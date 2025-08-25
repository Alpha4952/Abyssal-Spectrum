package alpheta.abyssal_spectrum.item.custom.crystal;

import blue.endless.jankson.annotation.Nullable;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class CrystalItemRenderer extends GeoItemRenderer<CrystalItem> {
    public CrystalItemRenderer() {
        super(new CrystalItemModel());
    }

    @Override
    public RenderLayer getRenderType(CrystalItem animatable, Identifier texture, @Nullable VertexConsumerProvider bufferSource, float partialTick) {
        return RenderLayer.getEntityTranslucent(texture);
    }
}
