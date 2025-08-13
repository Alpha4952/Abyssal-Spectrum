package alpheta.abyssal_spectrum;

import alpheta.abyssal_spectrum.block.ModBlocks;
import alpheta.abyssal_spectrum.item.ModGroups;
import alpheta.abyssal_spectrum.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbyssalSpectrum implements ModInitializer {
	public static final String MOD_ID = "abyssal_spectrum";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModGroups.registerModGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
	}
}