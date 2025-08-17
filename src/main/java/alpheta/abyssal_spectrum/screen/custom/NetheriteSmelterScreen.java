package alpheta.abyssal_spectrum.screen.custom;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class NetheriteSmelterScreen extends HandledScreen<NetheriteSmelterScreenHandler> {
    private static final Identifier GUI_TEXTURE = Identifier.of(AbyssalSpectrum.MOD_ID, "textures/gui/netherite_smelter/netherite_smelter_gui.png");
    private static final Identifier ARROW_TEXTURE = Identifier.of(AbyssalSpectrum.MOD_ID, "textures/gui/netherite_smelter/netherite_smelter_gui_arrow.png");
    private static final Identifier FIRE_TEXTURE = Identifier.of(AbyssalSpectrum.MOD_ID, "textures/gui/netherite_smelter/netherite_smelter_gui_fire.png");

    public NetheriteSmelterScreen(NetheriteSmelterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - 176) / 2;
        int y = (height - 166) / 2;

        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, 176, 166, 176, 166);

        renderProgressArrow(context, x, y);
        renderFuel(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(ARROW_TEXTURE, x + 80, y + 35, 0, 0, handler.getScaledArrowProgress(), 15, 21, 14);
        }
    }

    private void renderFuel(DrawContext context, int x, int y) {
        if (handler.hasFuel()) {
            int fuelHeight = handler.getScaledFuel();
            int drawY = y + 36 + (14 - fuelHeight);
            int srcV = 14 - fuelHeight;
            context.drawTexture(FIRE_TEXTURE, x + 49, drawY, 0, srcV, 14, fuelHeight, 14, 14);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
