package alpheta.abyssal_spectrum.datagen;

import alpheta.abyssal_spectrum.block.ModBlocks;
import alpheta.abyssal_spectrum.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.abyssal_steel_block)
                .add(ModBlocks.stellarite_block)
                .add(ModBlocks.netherite_smelter);


        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_ABYSSAL_STEEL_TOOL)
                .add(ModBlocks.abyssal_steel_block);

        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_ABYSSALITE_TOOL)
                ;

        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_PYRONITE_TOOL)
                ;

        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_VOIDSTEEL_TOOL)
                ;

        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_STELLARITE_TOOL)
                .add(ModBlocks.stellarite_block);

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ABYSSAL_STEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ABYSSALITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_PYRONITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_VOIDSTEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STELLARITE_TOOL);

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ABYSSAL_STEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ABYSSALITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_PYRONITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_VOIDSTEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STELLARITE_TOOL);


        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ABYSSAL_STEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ABYSSALITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_PYRONITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_VOIDSTEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STELLARITE_TOOL);

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ABYSSAL_STEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ABYSSALITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_PYRONITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_VOIDSTEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STELLARITE_TOOL);

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ABYSSAL_STEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ABYSSALITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_PYRONITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_VOIDSTEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STELLARITE_TOOL);

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ABYSSAL_STEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ABYSSALITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_PYRONITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_VOIDSTEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STELLARITE_TOOL);

        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_ABYSSAL_STEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_ABYSSALITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_PYRONITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_VOIDSTEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STELLARITE_TOOL);

        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_ABYSSALITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_PYRONITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_VOIDSTEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STELLARITE_TOOL);

        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_PYRONITE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_VOIDSTEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STELLARITE_TOOL);

        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_VOIDSTEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STELLARITE_TOOL);

        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_STELLARITE_TOOL);
    }
}
