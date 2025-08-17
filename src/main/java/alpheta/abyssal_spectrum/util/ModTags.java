package alpheta.abyssal_spectrum.util;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_ABYSSAL_STEEL_TOOL = createTag("needs_abyssal_steel_tool");
        public static final TagKey<Block> INCORRECT_FOR_ABYSSAL_STEEL_TOOL = createTag("incorrect_for_abyssal_steel_tool");
        public static final TagKey<Block> NEEDS_ABYSSALITE_TOOL = createTag("needs_abyssalite_tool");
        public static final TagKey<Block> INCORRECT_FOR_ABYSSALITE_TOOL = createTag("needs_abyssalite_tool");
        public static final TagKey<Block> NEEDS_PYRONITE_TOOL = createTag("needs_pyronite_tool");
        public static final TagKey<Block> INCORRECT_FOR_PYRONITE_TOOL = createTag("needs_pyronite_tool");
        public static final TagKey<Block> NEEDS_VOIDSTEEL_TOOL = createTag("needs_voidsteel_tool");
        public static final TagKey<Block> INCORRECT_FOR_VOIDSTEEL_TOOL = createTag("needs_voidsteel_tool");
        public static final TagKey<Block> NEEDS_STELLARITE_TOOL = createTag("needs_stellarite_tool");
        public static final TagKey<Block> INCORRECT_FOR_STELLARITE_TOOL = createTag("needs_stellarite_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(AbyssalSpectrum.MOD_ID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(AbyssalSpectrum.MOD_ID, name));
        }
    }
}
