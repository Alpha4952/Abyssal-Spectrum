package alpheta.abyssal_spectrum.item;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item abyssal_steel_ingot = registerItem("abyssal_steel_ingot", new Item(new Item.Settings()));
    public static final Item abyssal_scrap = registerItem("abyssal_scrap", new Item(new Item.Settings()));
    public static final Item ebonite_ingot = registerItem("ebonite_ingot", new Item(new Item.Settings()));
    public static final Item pyronite_ingot = registerItem("pyronite_ingot", new Item(new Item.Settings()));
    public static final Item void_steel_ingot = registerItem("void_steel_ingot", new Item(new Item.Settings()));
    public static final Item stellarite_ingot = registerItem("stellarite_ingot", new Item(new Item.Settings()));

    public static final Item abyssal_steel_sword = registerItem("abyssal_steel_sword",
            new SwordItem(ModToolMaterials.ABYSSAL_STEEL, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ABYSSAL_STEEL, 3, -2.4f))
            )
    );
    public static final Item abyssal_steel_pickaxe = registerItem("abyssal_steel_pickaxe",
            new PickaxeItem(ModToolMaterials.ABYSSAL_STEEL, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.ABYSSAL_STEEL, 1, -2.8f))
            )
    );

    private static Item registerItem(String item_name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(AbyssalSpectrum.MOD_ID, item_name), item);
    }

    public static void registerModItems() {
        AbyssalSpectrum.LOGGER.info("Registering mod items for " + AbyssalSpectrum.MOD_ID);
    }
}
