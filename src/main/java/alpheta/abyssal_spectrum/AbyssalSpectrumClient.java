package alpheta.abyssal_spectrum;

import alpheta.abyssal_spectrum.block.entity.ModBlockEntities;
import alpheta.abyssal_spectrum.block.entity.renderer.AltarBlockEntityRenderer;
import alpheta.abyssal_spectrum.block.entity.renderer.CrystalHolderBlockEntityRenderer;
import alpheta.abyssal_spectrum.config.AbyssalSpectrumClientConfig;
import net.fabricmc.api.ClientModInitializer;
import alpheta.abyssal_spectrum.screen.ModScreenHandlers;
import alpheta.abyssal_spectrum.screen.custom.NetheriteSmelterScreen;
import alpheta.abyssal_spectrum.util.ModModelPredicates;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbyssalSpectrumClient implements ClientModInitializer {
    public static final String MOD_ID = "abyssal_spectrum_client";
    public static final AbyssalSpectrumClientConfig CLIENT_CONFIG = AbyssalSpectrumClientConfig.createAndLoad();

    @Override
    public void onInitializeClient() {
        ModModelPredicates.registerModelPredicates();

        HandledScreens.register(ModScreenHandlers.NETHERITE_SMELTER_SCREEN_HANDLER, NetheriteSmelterScreen::new);

        BlockEntityRendererFactories.register(ModBlockEntities.ALTAR_BE, AltarBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.CRYSTAL_HOLDER_BE, CrystalHolderBlockEntityRenderer::new);
    }
}