package alpheta.abyssal_spectrum.item;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item stellarite_ingot = registerItem("stellarite_ingot", new Item(new Item.Settings()));

    private static Item registerItem(String item_name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(AbyssalSpectrum.MOD_ID, item_name), item);
    }

    public static void registerModItems() {
        AbyssalSpectrum.LOGGER.info("Registering mod items for " + AbyssalSpectrum.MOD_ID);
    }
}
