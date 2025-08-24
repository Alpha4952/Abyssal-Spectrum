package alpheta.abyssal_spectrum.util;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

// This was done using some source code from the mod Loot Beam
public abstract class BeamRenderer extends RenderLayer {
    private static final Identifier LOOT_BEAM_TEXTURE = Identifier.of(AbyssalSpectrum.MOD_ID, "textures/entity/beam.png");
    private static final RenderLayer LOOT_BEAM_RENDER_TYPE = createRenderType();

    public BeamRenderer(String string, VertexFormat vertexFormat, VertexFormat.DrawMode mode, int i, boolean bl, boolean bl2, Runnable runnable, Runnable runnable2) {
        super(string, vertexFormat, mode, i, bl, bl2, runnable, runnable2);
    }

    public static void renderLootBeam(MatrixStack stack, VertexConsumerProvider buffer, int red, int green, int blue) {
        float beamAlpha = 0.5f;

        var player = MinecraftClient.getInstance().player;
        assert player != null;

        float glowAlpha = beamAlpha * 0.4f;

        float beamRadius = 0.5f;
        float glowRadius = beamRadius + (beamRadius * 0.2f);
        float beamHeight = 69; // WHY NOT!
        float yOffset = 0;

        float R = red / 255f;
        float G = green / 255f;
        float B = blue / 255f;

        stack.push();

        //Render main beam
        stack.push();
        stack.translate(0, yOffset, 0);
        stack.translate(0, 1, 0);
        stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        renderPart(stack, buffer.getBuffer(LOOT_BEAM_RENDER_TYPE), R, G, B, beamAlpha, beamHeight, 0.0F, beamRadius, beamRadius, 0.0F, -beamRadius, 0.0F, 0.0F, -beamRadius);
        stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-180));
        renderPart(stack, buffer.getBuffer(LOOT_BEAM_RENDER_TYPE), R, G, B, beamAlpha, beamHeight, 0.0F, beamRadius, beamRadius, 0.0F, -beamRadius, 0.0F, 0.0F, -beamRadius);
        stack.pop();

        //Render glow around main beam
        stack.translate(0, yOffset, 0);
        stack.translate(0, 1, 0);
        stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        renderPart(stack, buffer.getBuffer(LOOT_BEAM_RENDER_TYPE), R, G, B, glowAlpha, beamHeight, -glowRadius, -glowRadius, glowRadius, -glowRadius, -beamRadius, glowRadius, glowRadius, glowRadius);
        stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-180));
        renderPart(stack, buffer.getBuffer(LOOT_BEAM_RENDER_TYPE), R, G, B, glowAlpha, beamHeight, -glowRadius, -glowRadius, glowRadius, -glowRadius, -beamRadius, glowRadius, glowRadius, glowRadius);

        stack.pop();
    }

    private static void renderPart(MatrixStack stack, VertexConsumer builder, float red, float green, float blue, float alpha, float height, float radius_1, float radius_2, float radius_3, float radius_4, float radius_5, float radius_6, float radius_7, float radius_8) {
        MatrixStack.Entry matrixentry = stack.peek();
        Matrix4f matrix_pose = matrixentry.getPositionMatrix();
        Matrix3f matrix_normal = matrixentry.getNormalMatrix();
        renderQuad(matrix_pose, matrix_normal, builder, red, green, blue, alpha, height, radius_1, radius_2, radius_3, radius_4);
        renderQuad(matrix_pose, matrix_normal, builder, red, green, blue, alpha, height, radius_7, radius_8, radius_5, radius_6);
        renderQuad(matrix_pose, matrix_normal, builder, red, green, blue, alpha, height, radius_3, radius_4, radius_7, radius_8);
        renderQuad(matrix_pose, matrix_normal, builder, red, green, blue, alpha, height, radius_5, radius_6, radius_1, radius_2);
    }

    private static void renderQuad(Matrix4f pose, Matrix3f normal, VertexConsumer builder, float red, float green, float blue, float alpha, float y, float z1, float text_u1, float z, float text_u) {
        addVertex(pose, normal, builder, red, green, blue, alpha, y, z1, text_u1, 1f, 0f);
        addVertex(pose, normal, builder, red, green, blue, alpha, 0f, z1, text_u1, 1f, 1f);
        addVertex(pose, normal, builder, red, green, blue, alpha, 0f, z, text_u, 0f, 1f);
        addVertex(pose, normal, builder, red, green, blue, alpha, y, z, text_u, 0f, 0f);
    }

    private static void addVertex(Matrix4f pose, Matrix3f normalMatrix, VertexConsumer builder, float red, float green, float blue, float alpha, float y, float x, float z, float texU, float texV) {
        normalMatrix.transform(new Vector3f(0.0F, 1.0F, 0.0F));

        builder.vertex(pose, x, y, z)
                .color(red, green, blue, alpha)
                .texture(texU, texV)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(15728880)
                .normal(0.0f, 1.0f, 0.0f);
    }

    private static String toBinaryName(String mapName){
        return "L" + mapName.replace('.', '/') + ";";
    }


    private static RenderLayer createRenderType() {
        RenderLayer.MultiPhaseParameters params = RenderLayer.MultiPhaseParameters.builder()
                .texture(new RenderPhase.Texture(LOOT_BEAM_TEXTURE, false, false))
                .transparency(RenderLayer.TRANSLUCENT_TRANSPARENCY)
                .lightmap(RenderLayer.ENABLE_LIGHTMAP)
                .overlay(RenderPhase.DISABLE_OVERLAY_COLOR)
                .writeMaskState(RenderLayer.COLOR_MASK)
                .depthTest(RenderPhase.LEQUAL_DEPTH_TEST)
                .program(RenderLayer.TRANSLUCENT_PROGRAM)
                .build(false);

        return RenderLayer.of(
                "beam",
                VertexFormats.POSITION_COLOR_TEXTURE_LIGHT_NORMAL,
                VertexFormat.DrawMode.QUADS,
                256,
                false,
                true,
                params
        );
    }
}