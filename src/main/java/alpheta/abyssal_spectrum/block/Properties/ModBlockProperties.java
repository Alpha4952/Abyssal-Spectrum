package alpheta.abyssal_spectrum.block.Properties;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;

public class ModBlockProperties extends Properties {
    public static final IntProperty LEVEL = IntProperty.of("level", 0, 5);

    public static void registerModBlockProperties() {
        AbyssalSpectrum.LOGGER.info("Registering mod blocks for " + AbyssalSpectrum.MOD_ID);
    }
}
