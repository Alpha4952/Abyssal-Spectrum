package alpheta.abyssal_spectrum;

import alpheta.abyssal_spectrum.config.AbyssalSpectrumClientConfig;
import net.fabricmc.api.ClientModInitializer;
import alpheta.abyssal_spectrum.screen.ModScreenHandlers;
import alpheta.abyssal_spectrum.screen.custom.NetheriteSmelterScreen;
import alpheta.abyssal_spectrum.util.ModModelPredicates;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbyssalSpectrumClient implements ClientModInitializer {
    public static final String MOD_ID = "abyssal_spectrum_client";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final AbyssalSpectrumClientConfig CLIENT_CONFIG = AbyssalSpectrumClientConfig.createAndLoad();

    @Override
    public void onInitializeClient() {
        ModModelPredicates.registerModelPredicates();

        HandledScreens.register(ModScreenHandlers.NETHERITE_SMELTER_SCREEN_HANDLER, NetheriteSmelterScreen::new);
    }
}