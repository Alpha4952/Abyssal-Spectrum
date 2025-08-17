package alpheta.abyssal_spectrum.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import alpheta.abyssal_spectrum.AbyssalSpectrum;
import alpheta.abyssal_spectrum.screen.custom.NetheriteSmelterScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static ScreenHandlerType<NetheriteSmelterScreenHandler> NETHERITE_SMELTER_SCREEN_HANDLER;


    public static void registerScreenHandlers() {
        NETHERITE_SMELTER_SCREEN_HANDLER =
                Registry.register(Registries.SCREEN_HANDLER, Identifier.of(AbyssalSpectrum.MOD_ID, "netherite_smelter_screen_handler"),
                        new ExtendedScreenHandlerType<>(NetheriteSmelterScreenHandler::new, BlockPos.PACKET_CODEC));

        AbyssalSpectrum.LOGGER.info("Registering Screen Handlers for " + AbyssalSpectrum.MOD_ID);
    }
}