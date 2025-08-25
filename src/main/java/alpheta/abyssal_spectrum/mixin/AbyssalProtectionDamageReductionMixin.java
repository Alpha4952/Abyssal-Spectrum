package alpheta.abyssal_spectrum.mixin;

import alpheta.abyssal_spectrum.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class AbyssalProtectionDamageReductionMixin {
    @Inject(method = "modifyAppliedDamage", at = @At("HEAD"), cancellable = true)
    private void applyDamage(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity.hasStatusEffect(ModEffects.abyssal_protection)) {
            float damageReduction;
            if (source.getAttacker() instanceof net.minecraft.entity.mob.WardenEntity) {
                damageReduction = amount * 0.3f;
            } else {
                damageReduction = amount * 0.8f;
            }

            cir.setReturnValue(damageReduction);
        }
    }
}
