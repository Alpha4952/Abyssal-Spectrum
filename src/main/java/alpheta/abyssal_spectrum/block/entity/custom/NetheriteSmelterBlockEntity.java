package alpheta.abyssal_spectrum.block.entity.custom;

import alpheta.abyssal_spectrum.block.custom.NetheriteSmelterBlock;
import alpheta.abyssal_spectrum.recipe.ModRecipes;
import alpheta.abyssal_spectrum.recipe.ReactorInput;
import alpheta.abyssal_spectrum.recipe.ReactorRecipe;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import alpheta.abyssal_spectrum.block.entity.ImplementedInventory;
import alpheta.abyssal_spectrum.block.entity.ModBlockEntities;
import alpheta.abyssal_spectrum.screen.custom.NetheriteSmelterScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class NetheriteSmelterBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    private static final int INPUT_SLOT1 = 0;
    private static final int INPUT_SLOT2 = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int INPUT_FUEL = 3;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 600;
    private int fuel = 0;
    private int maxFuel = 1200;

    public NetheriteSmelterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NETHERITE_SMELTER_BE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> NetheriteSmelterBlockEntity.this.progress;
                    case 1 -> NetheriteSmelterBlockEntity.this.maxProgress;
                    case 2 -> NetheriteSmelterBlockEntity.this.fuel;
                    case 3 -> NetheriteSmelterBlockEntity.this.maxFuel;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> NetheriteSmelterBlockEntity.this.progress = value;
                    case 1 -> NetheriteSmelterBlockEntity.this.maxProgress = value;
                    case 2 -> NetheriteSmelterBlockEntity.this.fuel = value;
                    case 3 -> NetheriteSmelterBlockEntity.this.maxFuel = value;
                }
            }

            @Override
            public int size() {
                return 4;
            }
        };
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.abyssal_spectrum.netherite_smelter", Colors.WHITE);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new NetheriteSmelterScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("netherite_smelter.progress", progress);
        nbt.putInt("netherite_smelter.max_progress", maxProgress);
        nbt.putInt("netherite_smelter.fuel", fuel);
        nbt.putInt("netherite_smelter.max_fuel", maxFuel);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("netherite_smelter.progress");
        maxProgress = nbt.getInt("netherite_smelter.max_progress");
        fuel = nbt.getInt("netherite_smelter.fuel");
        maxFuel = nbt.getInt("netherite_smelter.max_fuel");
        super.readNbt(nbt, registryLookup);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        boolean lit = fuel > 0;
        if (this.getCachedState().get(NetheriteSmelterBlock.LIT) != lit) {
            world.setBlockState(pos, this.getCachedState().with(NetheriteSmelterBlock.LIT, lit), 3);
        }

        if (this.fuel == 0 && hasRecipe() && hasFuel()) {
            this.fuel = 1200;
            this.removeStack(INPUT_FUEL, 1);
            this.setStack(INPUT_FUEL, new ItemStack(Items.BUCKET, 1));
        } else if (fuel > 0) {
            reduceFuelLevel();
            if (hasRecipe()) {
                increaseCraftingProgress();
                markDirty(world, pos, state);

                if (hasCraftingFinished()) {
                    craftItem();
                    resetProgress();
                }
            } else {
                resetProgress();
            }
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 600;
    }

    private void craftItem() {
        Optional<RecipeEntry<ReactorRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return;
        }


        ItemStack output = recipe.get().value().output();

        this.removeStack(INPUT_SLOT1, 1);
        this.removeStack(INPUT_SLOT2, 1);
        this.setStack(OUTPUT_SLOT, new ItemStack(output.getItem(),
                this.getStack(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private void reduceFuelLevel() {
        this.fuel--;
    }

    private boolean hasFuel() {
        Item fuel = Items.LAVA_BUCKET;

        return this.getStack(INPUT_FUEL).isOf(fuel);
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<ReactorRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().output();

        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeEntry<ReactorRecipe>> getCurrentRecipe() {
        assert this.getWorld() != null;
        return this.getWorld().getRecipeManager().getFirstMatch(ModRecipes.REACTOR_TYPE, new ReactorInput(inventory.get(INPUT_SLOT1), inventory.get(INPUT_SLOT2)), this.getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = this.getStack(OUTPUT_SLOT).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT).getMaxCount();
        int currentCount = this.getStack(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}