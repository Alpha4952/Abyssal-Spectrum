package alpheta.abyssal_spectrum.item;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import alpheta.abyssal_spectrum.item.custom.crystal.CrystalItem;
import alpheta.abyssal_spectrum.item.custom.abyssal_steel_armor.AbyssalSteelArmorItem;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item abyssal_scrap = registerItem("abyssal_scrap", new Item(new Item.Settings()));
    public static final Item abyssal_steel_ingot = registerItem("abyssal_steel_ingot", new Item(new Item.Settings()));
    public static final Item ebonite_scrap = registerItem("ebonite_scrap", new Item(new Item.Settings()));
    public static final Item ebonite_ingot = registerItem("ebonite_ingot", new Item(new Item.Settings()));
    public static final Item pyronite_ingot = registerItem("pyronite_ingot", new Item(new Item.Settings()));
    public static final Item voidsteel_ingot = registerItem("void_steel_ingot", new Item(new Item.Settings()));
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
    public static final Item abyssal_steel_boots = registerItem("abyssal_steel_boots",
            new AbyssalSteelArmorItem(ModArmorMaterials.ABYSSAL_STEEL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(1028)));
    public static final Item abyssal_steel_leggings = registerItem("abyssal_steel_leggings",
            new AbyssalSteelArmorItem(ModArmorMaterials.ABYSSAL_STEEL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(1028)));
    public static final Item abyssal_steel_chestplate = registerItem("abyssal_steel_chestplate",
            new AbyssalSteelArmorItem(ModArmorMaterials.ABYSSAL_STEEL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(1028)));
    public static final Item abyssal_steel_helmet = registerItem("abyssal_steel_helmet",
            new AbyssalSteelArmorItem(ModArmorMaterials.ABYSSAL_STEEL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(1028)));

    public static final Item abyssalite_sword = registerItem("abyssalite_sword",
            new SwordItem(ModToolMaterials.ABYSSALITE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ABYSSALITE, 3, -2.4f))
            )
    );
    public static final Item abyssalite_pickaxe = registerItem("abyssalite_pickaxe",
            new PickaxeItem(ModToolMaterials.ABYSSALITE, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.ABYSSALITE, 1, -2.8f))
            )
    );

    public static final Item pyronite_sword = registerItem("pyronite_sword",
            new SwordItem(ModToolMaterials.PYRONITE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.PYRONITE, 3, -2.4f))
            )
    );
    public static final Item pyronite_pickaxe = registerItem("pyronite_pickaxe",
            new PickaxeItem(ModToolMaterials.PYRONITE, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.PYRONITE, 1, -2.8f))
            )
    );

    public static final Item voidsteel_sword = registerItem("voidsteel_sword",
            new SwordItem(ModToolMaterials.VOIDSTEEL, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.VOIDSTEEL, 3, -2.4f))
            )
    );
    public static final Item voidsteel_pickaxe = registerItem("voidsteel_pickaxe",
            new PickaxeItem(ModToolMaterials.VOIDSTEEL, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.VOIDSTEEL, 1, -2.8f))
            )
    );

    public static final Item stellarite_sword = registerItem("stellarite_sword",
            new SwordItem(ModToolMaterials.STELLARITE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.STELLARITE, 3, -2.4f))
            )
    );
    public static final Item stellarite_pickaxe = registerItem("stellarite_pickaxe",
            new PickaxeItem(ModToolMaterials.STELLARITE, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.STELLARITE, 1, -2.8f))
            )
    );

    public static final Item depleted_crystal = registerItem("depleted_crystal", new CrystalItem(new Item.Settings()));
    public static final Item abyssal_crystal = registerItem("abyssal_steel_upgrade_crystal", new CrystalItem(new Item.Settings()));


    private static Item registerItem(String item_name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(AbyssalSpectrum.MOD_ID, item_name), item);
    }

    public static void registerModItems() {
        AbyssalSpectrum.LOGGER.info("Registering mod items for " + AbyssalSpectrum.MOD_ID);
    }
}
