package alpheta.abyssal_spectrum.block.entity.custom;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CrystalHolderBlockEntityModel extends GeoModel<CrystalHolderBlockEntity> {
    @Override
    public Identifier getModelResource(CrystalHolderBlockEntity animatable) {
        return Identifier.of(AbyssalSpectrum.MOD_ID, "geo/block/crystal_holder.geo.json");
    }

    @Override
    public Identifier getTextureResource(CrystalHolderBlockEntity animatable) {
        return Identifier.of(AbyssalSpectrum.MOD_ID, "textures/block/crystal_holder.png");
    }

    @Override
    public Identifier getAnimationResource(CrystalHolderBlockEntity animatable) {
        return Identifier.of(AbyssalSpectrum.MOD_ID, "animations/block/crystal_holder.animation.json");
    }
}