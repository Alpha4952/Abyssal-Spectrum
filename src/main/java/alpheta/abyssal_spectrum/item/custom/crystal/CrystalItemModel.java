package alpheta.abyssal_spectrum.item.custom.crystal;


import alpheta.abyssal_spectrum.AbyssalSpectrum;
import alpheta.abyssal_spectrum.item.ModItems;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CrystalItemModel extends GeoModel<CrystalItem> {
    @Override
    public Identifier getModelResource(CrystalItem animatable) {
        return Identifier.of(AbyssalSpectrum.MOD_ID, "geo/item/crystal.geo.json");
    }

    @Override
    public Identifier getTextureResource(CrystalItem animatable) {
        if (animatable.equals(ModItems.abyssal_crystal)) {
            return Identifier.of(AbyssalSpectrum.MOD_ID, "textures/item/crystal/abyssal_crystal.png");
        }

        return Identifier.of(AbyssalSpectrum.MOD_ID, "textures/item/crystal/depleted_crystal.png");
    }

    @Override
    public Identifier getAnimationResource(CrystalItem animatable) {
        return Identifier.of(AbyssalSpectrum.MOD_ID, "animations/item/crystal.animation.json");
    }
}
