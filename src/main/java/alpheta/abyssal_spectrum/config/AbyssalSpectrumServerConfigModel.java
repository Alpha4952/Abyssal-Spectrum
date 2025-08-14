package alpheta.abyssal_spectrum.config;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Sync;

@Sync(Option.SyncMode.OVERRIDE_CLIENT)
@Config(name = "abyssal_spectrum_server", wrapperName = "AbyssalSpectrumServerConfig")
public class AbyssalSpectrumServerConfigModel {
    public int ebonite_ingots_from_elder_guardian = 1;
    public float ebonite_ingots_drop_chance = 0.25f;
    public float ebonite_ingots_looting_bonus = 0.25f;
}
