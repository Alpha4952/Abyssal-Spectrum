package alpheta.abyssal_spectrum.effect;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects extends StatusEffectInstance {
    public static final RegistryEntry<StatusEffect> abyssal_protection = registerStatusEffect(
            "abyssal_protection",
            new AbyssalProtectionEffect(StatusEffectCategory.BENEFICIAL,
            0x8839ef)
    );

    public ModEffects(RegistryEntry<StatusEffect> effect) {
        super(effect);
    }

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(AbyssalSpectrum.MOD_ID, name), statusEffect);
    }

    public static void registerEffect() {
        AbyssalSpectrum.LOGGER.info("Registering mod effects for " + AbyssalSpectrum.MOD_ID);
    }
}