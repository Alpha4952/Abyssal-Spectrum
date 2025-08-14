package alpheta.abyssal_spectrum.config;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Sync;

@Config(name = "abyssal_spectrum_client", wrapperName = "AbyssalSpectrumClientConfig")
@Sync(Option.SyncMode.NONE)
public class AbyssalSpectrumClientConfigModel {
    public boolean show_hud_overlay = true;
}
