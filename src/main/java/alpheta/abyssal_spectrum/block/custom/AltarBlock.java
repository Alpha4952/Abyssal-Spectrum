package alpheta.abyssal_spectrum.block.custom;

import alpheta.abyssal_spectrum.block.entity.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import alpheta.abyssal_spectrum.block.entity.custom.AltarBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AltarBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final BooleanProperty LIT = Properties.LIT;

    private static final VoxelShape SHAPE =
            Block.createCuboidShape(1, 0, 1, 15, 16, 15);
    public static final MapCodec<AltarBlock> CODEC = AltarBlock.createCodec(AltarBlock::new);

    public AltarBlock(Settings settings) {
        super(settings.luminance(state -> state.get(LIT) ? 15 : 0));
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(LIT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof AltarBlockEntity) {
                ItemScatterer.spawn(world, pos, ((AltarBlockEntity) blockEntity));
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof AltarBlockEntity AltarBlockEntity) {
            if (AltarBlockEntity.isEmpty() && !stack.isEmpty()) {
                AltarBlockEntity.setStack(0, stack.copy());
                stack.setCount(0);
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 2f);

                AltarBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);

                return ItemActionResult.SUCCESS;
            } else if (!AltarBlockEntity.isEmpty() && stack.isEmpty() && !player.isSneaking()) {
                player.giveItemStack(AltarBlockEntity.getStack(0));
                AltarBlockEntity.clear();
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 1f);

                AltarBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);

                return ItemActionResult.SUCCESS;
            } else if (!AltarBlockEntity.isEmpty() && !stack.isEmpty() && ItemStack.areItemsEqual(stack, AltarBlockEntity.getStack(0))) {
                int max = AltarBlockEntity.getStack(0).getMaxCount();
                int transfer = Math.min(stack.getCount(), max - AltarBlockEntity.getStack(0).getCount());

                if (transfer > 0) {
                    AltarBlockEntity.getStack(0).increment(transfer);
                    stack.decrement(transfer);

                    AltarBlockEntity.markDirty();
                    world.updateListeners(pos, state, state, 0);
                    world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 1f);
                }

                return ItemActionResult.SUCCESS;
            } else if (!AltarBlockEntity.isEmpty() && !stack.isEmpty() && !ItemStack.areItemsEqual(stack, AltarBlockEntity.getStack(0))) {
                ItemStack stackOnAltar = AltarBlockEntity.getStack(0);
                AltarBlockEntity.setStack(0, stack);
                player.setStackInHand(Hand.MAIN_HAND, stackOnAltar);
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 1f);

                AltarBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);

                return ItemActionResult.SUCCESS;
            }
        }

        return ItemActionResult.FAIL;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AltarBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if(world.isClient()) {
            return null;
        }

        return validateTicker(type, ModBlockEntities.ALTAR_BE,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
}