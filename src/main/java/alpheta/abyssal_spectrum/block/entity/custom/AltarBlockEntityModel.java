package alpheta.abyssal_spectrum.block.entity.custom;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import alpheta.abyssal_spectrum.block.custom.AltarBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class AltarBlockEntityModel extends GeoModel<AltarBlockEntity> {
    @Override
    public Identifier getModelResource(AltarBlockEntity animatable) {
        BlockState state = animatable.getCachedState();

        if (!state.get(AltarBlock.LIT)) return Identifier.of(AbyssalSpectrum.MOD_ID, "geo/block/altar_no_circle.geo.json");
        return Identifier.of(AbyssalSpectrum.MOD_ID, "geo/block/altar.geo.json");
    }

    @Override
    public Identifier getTextureResource(AltarBlockEntity animatable) {
        BlockState state = animatable.getCachedState();

        int level = state.get(AltarBlock.LEVEL);
        return Identifier.of(AbyssalSpectrum.MOD_ID, "textures/block/altar/altar_level_" + level + ".png");
    }

    @Override
    public Identifier getAnimationResource(AltarBlockEntity animatable) {
        return Identifier.of(AbyssalSpectrum.MOD_ID, "animations/block/altar.animation.json");
    }
}