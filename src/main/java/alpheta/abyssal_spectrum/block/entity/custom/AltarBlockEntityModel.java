package alpheta.abyssal_spectrum.block.entity.custom;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class AltarBlockEntityModel extends GeoModel<AltarBlockEntity> {
    @Override
    public Identifier getModelResource(AltarBlockEntity animatable) {
        return Identifier.of(AbyssalSpectrum.MOD_ID, "geo/block/altar.geo.json");
    }

    @Override
    public Identifier getTextureResource(AltarBlockEntity animatable) {
        return Identifier.of(AbyssalSpectrum.MOD_ID, "textures/block/altar.png");
    }

    @Override
    public Identifier getAnimationResource(AltarBlockEntity animatable) {
        return Identifier.of(AbyssalSpectrum.MOD_ID, "animations/block/altar.animation.json");
    }
}