package alpheta.abyssal_spectrum.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;

import java.util.List;

public class AbyssalSteelArmorMaterial extends ArmorItem {
    public AbyssalSteelArmorMaterial(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.armor.abyssal_spectrum.abyssal_steel1"));
            tooltip.add(Text.translatable("tooltip.armor.abyssal_spectrum.abyssal_steel2"));
        } else {
            tooltip.add(Text.translatable("tooltip.abyssal_spectrum.needs_shift"));
        }
    }
}
