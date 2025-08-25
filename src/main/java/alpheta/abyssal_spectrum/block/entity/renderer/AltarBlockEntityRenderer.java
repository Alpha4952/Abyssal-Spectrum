package alpheta.abyssal_spectrum.block.entity.renderer;

import alpheta.abyssal_spectrum.block.custom.AltarBlock;
import alpheta.abyssal_spectrum.block.entity.custom.AltarBlockEntity;
import alpheta.abyssal_spectrum.block.entity.custom.AltarBlockEntityModel;
import alpheta.abyssal_spectrum.util.BeamRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class AltarBlockEntityRenderer extends GeoBlockRenderer<AltarBlockEntity> {
    public AltarBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        super(new AltarBlockEntityModel());
    }

    @Override
    public void render(AltarBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        super.render(entity, tickDelta, matrices, vertexConsumers, light, overlay);

        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = entity.getStack(0);

        matrices.push();
        matrices.translate(0.5f, 1.25f, 0.5f);
        matrices.scale(0.5f, 0.5f, 0.5f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getRenderingRotation()));

        assert entity.getWorld() != null;
        if (entity.getCachedState().get(AltarBlock.LIT)) {
            int red = 0, green = 0, blue = 0;
            switch (entity.getCachedState().get(AltarBlock.LEVEL)) {
                case 1: {
                    red = 26;
                    green = 193;
                    blue = 201;
                    break;
                }
                case 2: {
                    red = 149;
                    green = 207;
                    blue = 199;
                    break;
                }
                case 3: {
                    red = 255;
                    green = 255;
                    blue = 255;
                    break;
                }
                case 4: {
                    red = 254;
                    green = 255;
                    blue = 255;
                    break;
                }
                case 5: {
                    red = 253;
                    green = 255;
                    blue = 255;
                    break;
                }
            }

            BeamRenderer.renderLootBeam(
                    matrices,
                    vertexConsumers,
                    red,
                    green,
                    blue
            );
        }

        assert entity.getWorld() != null;
        itemRenderer.renderItem(stack, ModelTransformationMode.GROUND, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}