package alpheta.abyssal_spectrum.recipe;

import alpheta.abyssal_spectrum.AbyssalSpectrum;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<ReactorRecipe> REACTOR_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(AbyssalSpectrum.MOD_ID, "reactor"),
            new ReactorRecipe.Serializer());

    public static final RecipeType<ReactorRecipe> REACTOR_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(AbyssalSpectrum.MOD_ID, "reactor"), new RecipeType<ReactorRecipe>() {
                @Override
                public String toString() {
                    return "netherite_smelter";
                }
            });

    public static final RecipeSerializer<ReactorRecipe> ALTAR_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(AbyssalSpectrum.MOD_ID, "altar"),
            new ReactorRecipe.Serializer());

    public static final RecipeType<ReactorRecipe> ALTAR_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(AbyssalSpectrum.MOD_ID, "altar"), new RecipeType<ReactorRecipe>() {
                @Override
                public String toString() {
                    return "netherite_smelter";
                }
            });

    public static void registerRecipes() {
        AbyssalSpectrum.LOGGER.info("Registering Custom Recipes for " + AbyssalSpectrum.MOD_ID);
    }
}