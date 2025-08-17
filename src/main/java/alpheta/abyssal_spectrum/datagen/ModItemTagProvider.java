package alpheta.abyssal_spectrum.datagen;

import alpheta.abyssal_spectrum.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.abyssal_steel_sword);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.abyssal_steel_pickaxe);

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItems.abyssal_steel_helmet);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItems.abyssal_steel_chestplate);

        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(ModItems.abyssal_steel_leggings);

        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItems.abyssal_steel_boots);
    }
}
