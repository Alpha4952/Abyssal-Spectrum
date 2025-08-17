package alpheta.abyssal_spectrum.config;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Sync;

@Sync(Option.SyncMode.OVERRIDE_CLIENT)
@Config(name = "abyssal_spectrum_server", wrapperName = "AbyssalSpectrumServerConfig")
public class AbyssalSpectrumServerConfigModel {
    public float parry_window_seconds = 0.25f;
    public float parry_damage_multiplier = 1.75f;

    public int abyssal_scraps_from_warden = 1;

    public int ebonite_ingots_from_elder_guardian = 1;
    public float ebonite_ingots_drop_chance = 1f;
    public float ebonite_ingots_first_bonus = 0.5f;
    public float ebonite_ingots_second_bonus = 0.5f;
    public float ebonite_ingots_guardian_drop_chance = 0.01f;

    public boolean abyssal_reactor_requires_eclipse = true;
}
