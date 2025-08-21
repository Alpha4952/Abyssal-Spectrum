package alpheta.abyssal_spectrum.item.custom.abyssal_steel_armor;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public final class AbyssalSteelArmorRenderer extends GeoArmorRenderer<AbyssalSteelArmorItem> {
    public AbyssalSteelArmorRenderer() {
        super(new DefaultedItemGeoModel<>(Identifier.of(AbyssalSpectrum.MOD_ID, "armor/abyssal_steel_armor")));
    }
}