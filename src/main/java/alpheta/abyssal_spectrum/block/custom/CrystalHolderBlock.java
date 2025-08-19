package alpheta.abyssal_spectrum.block.custom;

import com.mojang.serialization.MapCodec;
import alpheta.abyssal_spectrum.block.entity.custom.CrystalHolderBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CrystalHolderBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE =
            Block.createCuboidShape(2, 0, 2, 14, 13, 14);
    public static final MapCodec<CrystalHolderBlock> CODEC = CrystalHolderBlock.createCodec(CrystalHolderBlock::new);

    public CrystalHolderBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CrystalHolderBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof CrystalHolderBlockEntity) {
                ItemScatterer.spawn(world, pos, ((CrystalHolderBlockEntity) blockEntity));
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
                                             PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof CrystalHolderBlockEntity CrystalHolderBlockEntity) {
            if (CrystalHolderBlockEntity.isEmpty() && !stack.isEmpty() && !player.isSneaking()) {
                CrystalHolderBlockEntity.setStack(0, stack.copyWithCount(1));
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 2f);
                stack.decrement(1);

                CrystalHolderBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);

                return ItemActionResult.SUCCESS;
            } else if (!CrystalHolderBlockEntity.isEmpty() && stack.isEmpty() && !player.isSneaking()) {
                ItemStack stackOnCrystalHolder = CrystalHolderBlockEntity.getStack(0);
                player.giveItemStack(stackOnCrystalHolder);
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 1f);
                CrystalHolderBlockEntity.clear();

                CrystalHolderBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);

                return ItemActionResult.SUCCESS;
            }
        }

        return ItemActionResult.FAIL;
    }
}