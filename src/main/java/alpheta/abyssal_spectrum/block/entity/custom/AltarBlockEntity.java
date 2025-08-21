package alpheta.abyssal_spectrum.block.entity.custom;

import alpheta.abyssal_spectrum.block.custom.AltarBlock;
import alpheta.abyssal_spectrum.block.entity.ImplementedInventory;
import alpheta.abyssal_spectrum.block.entity.ModBlockEntities;
import alpheta.abyssal_spectrum.item.ModItems;
import alpheta.abyssal_spectrum.recipe.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;

public class AltarBlockEntity extends BlockEntity implements ImplementedInventory, GeoBlockEntity {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private float rotation = 0;

    public AltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALTAR_BE, pos, state);
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    private int progress = 0;
    private int maxProgress = 600;

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putInt("altar.progress", progress);
        nbt.putInt("altar.max_progress", maxProgress);
        Inventories.writeNbt(nbt, inventory, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        progress = nbt.getInt("altar.progress");
        maxProgress = nbt.getInt("altar.max_progress");
        Inventories.readNbt(nbt, inventory, registryLookup);
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

    protected static final RawAnimation DEPLOY_ANIM = RawAnimation.begin().thenLoop("idle");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, this::deployAnimController));
    }

    protected <E extends AltarBlockEntity> PlayState deployAnimController(final AnimationState<E> state) {
        return state.setAndContinue(DEPLOY_ANIM);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        boolean working = progress > 0;
        if (this.getCachedState().get(AltarBlock.LIT) != working) {
            world.setBlockState(pos, this.getCachedState().with(AltarBlock.LIT, working), 3);
        }

        if (hasRecipe()) {
            increaseCraftingProgress();
            markDirty(world, pos, state);
            markDirty(world, pos.up().up(), state);

            if (hasCraftingFinished()) {
                craftItem();
                markDirty(world, pos, state);
                markDirty(world, pos.up().up(), state);
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 600;
    }

    private void craftItem() {
        Optional<RecipeEntry<AltarRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return;
        }

        assert this.world != null;

        BlockPos abovePos = this.pos.up().up();
        CrystalHolderBlockEntity crystalHolderBE = (CrystalHolderBlockEntity) this.world.getBlockEntity(abovePos);

        ItemStack output = recipe.get().value().output();

        this.removeStack(0);
        crystalHolderBE.setStack(0, new ItemStack(ModItems.depleted_crystal, 1));

        crystalHolderBE.markDirty();
        world.updateListeners(abovePos, crystalHolderBE.getCachedState(), crystalHolderBE.getCachedState(), 0);
        this.setStack(0, new ItemStack(output.getItem(),output.getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<AltarRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }
        if (recipe.get().value().getLevel() > 1) return false;

        ItemStack output = recipe.get().value().output();

        return true;
    }

    private Optional<RecipeEntry<AltarRecipe>> getCurrentRecipe() {
        assert this.getWorld() != null;
        assert this.world != null;

        ItemStack altarStack = this.getStack(0);

        BlockPos abovePos = this.pos.up().up();
        CrystalHolderBlockEntity crystalHolderBE = (CrystalHolderBlockEntity) this.world.getBlockEntity(abovePos);

        if (!(crystalHolderBE instanceof CrystalHolderBlockEntity CrystalHolderBE)) {
            return Optional.empty(); // no recipe possible without the crystal holder
        }

        ItemStack crystalStack = CrystalHolderBE.getStack(0);

        return this.getWorld().getRecipeManager().getFirstMatch(ModRecipes.ALTAR_TYPE, new AltarInput(altarStack, crystalStack), this.getWorld());
    }
}