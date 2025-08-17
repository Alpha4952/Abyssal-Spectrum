package alpheta.abyssal_spectrum.item;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import alpheta.abyssal_spectrum.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModGroups {
    public static final ItemGroup Abyssal_Spectrum_Item_Group = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(AbyssalSpectrum.MOD_ID, "items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.stellarite_ingot))
                    .displayName(Text.translatable("itemgroup.abyssal_spectrum.items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.abyssal_steel_ingot);
                        entries.add(ModItems.abyssal_scrap);
                        entries.add(ModBlocks.abyssal_steel_block);
                        entries.add(ModItems.ebonite_ingot);
                        entries.add(ModItems.pyronite_ingot);
                        entries.add(ModItems.voidsteel_ingot);
                        entries.add(ModItems.stellarite_ingot);
                        entries.add(ModBlocks.stellarite_block);

                        entries.add(ModItems.abyssal_steel_sword);
                        entries.add(ModItems.abyssal_steel_pickaxe);
                        entries.add(ModItems.abyssal_steel_helmet);
                        entries.add(ModItems.abyssal_steel_chestplate);
                        entries.add(ModItems.abyssal_steel_leggings);
                        entries.add(ModItems.abyssal_steel_boots);

                        entries.add(ModBlocks.netherite_smelter);
                    })
                    .build());

    public static void registerModGroups() {
        AbyssalSpectrum.LOGGER.info("Registering mod item groups for " + AbyssalSpectrum.MOD_ID);
    }
}
