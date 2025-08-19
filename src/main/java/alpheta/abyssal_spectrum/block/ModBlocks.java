package alpheta.abyssal_spectrum.block;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import alpheta.abyssal_spectrum.block.custom.AltarBlock;
import alpheta.abyssal_spectrum.block.custom.CrystalHolderBlock;
import alpheta.abyssal_spectrum.block.custom.NetheriteSmelterBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block abyssal_steel_block = registerBlock("abyssal_steel_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_AQUA)
                    .requiresTool()
                    .strength(60.0F, 2000.0F)
                    .sounds(BlockSoundGroup.NETHERITE))
    );

    public static final Block ebonite_block = registerBlock("ebonite_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.CYAN)
                    .requiresTool()
                    .strength(65.0F, 2000.0F)
                    .sounds(BlockSoundGroup.NETHERITE))
    );

    public static final Block stellarite_block = registerBlock("stellarite_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .requiresTool()
                    .strength(75.0F, 2000.0F)
                    .sounds(BlockSoundGroup.NETHERITE))
    );

    public static final Block netherite_smelter = registerBlock("netherite_smelter",
            new NetheriteSmelterBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .requiresTool()
                    .strength(50.0F, 2000.0F)
                    .sounds(BlockSoundGroup.LODESTONE))
    );

    public static final Block altar = registerBlock("altar",
            new AltarBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.WHITE)
                    .requiresTool()
                    .strength(25.0F, 2000.0F)
                    .sounds(BlockSoundGroup.LODESTONE)
                    .nonOpaque()
            ));

    public static final Block crystal_holder = registerBlock("crystal_holder",
            new CrystalHolderBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.WHITE)
                    .requiresTool()
                    .strength(25.0F, 2000.0F)
                    .sounds(BlockSoundGroup.LODESTONE)
                    .nonOpaque()
            ));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(AbyssalSpectrum.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(AbyssalSpectrum.MOD_ID, name),
                new BlockItem(block, new Item.Settings())
        );
    }

    public static void registerModBlocks() {
        AbyssalSpectrum.LOGGER.info("Registering mod blocks for " + AbyssalSpectrum.MOD_ID);
    }
}
