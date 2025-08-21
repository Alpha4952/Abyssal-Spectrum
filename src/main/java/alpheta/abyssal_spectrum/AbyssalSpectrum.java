package alpheta.abyssal_spectrum;

import alpheta.abyssal_spectrum.block.ModBlocks;
import alpheta.abyssal_spectrum.block.entity.ModBlockEntities;
import alpheta.abyssal_spectrum.config.AbyssalSpectrumServerConfig;
import alpheta.abyssal_spectrum.effect.ModEffects;
import alpheta.abyssal_spectrum.item.ModGroups;
import alpheta.abyssal_spectrum.item.ModItems;
import alpheta.abyssal_spectrum.item.ModLootTable;
import alpheta.abyssal_spectrum.recipe.ModRecipes;
import alpheta.abyssal_spectrum.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbyssalSpectrum implements ModInitializer {
	public static final String MOD_ID = "abyssal_spectrum";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final AbyssalSpectrumServerConfig SERVER_CONFIG = AbyssalSpectrumServerConfig.createAndLoad();

	@Override
	public void onInitialize() {
        ModGroups.registerModGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerBlockEntities();

        ModLootTable.modifyLootTables();

        ModScreenHandlers.registerScreenHandlers();

        ModRecipes.registerRecipes();

        ModEffects.registerEffect();
	}
}