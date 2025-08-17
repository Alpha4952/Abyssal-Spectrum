package alpheta.abyssal_spectrum.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record AltarRecipe(Ingredient input1, Ingredient input2, ItemStack output) implements Recipe<AltarInput> {

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(input1);
        list.add(input2);
        return list;
    }

    @Override
    public boolean matches(AltarInput input, World world) {
        if (world.isClient()) return false;

        ItemStack stack1 = input.getStackInSlot(0);
        ItemStack stack2 = input.getStackInSlot(1);

        // match either order
        return (input1.test(stack1) && input2.test(stack2)) || (input1.test(stack2) && input2.test(stack1));
    }

    @Override
    public ItemStack craft(AltarInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true; // not grid-based, always fits
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.ALTAR_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.ALTAR_TYPE;
    }

    // --- Serializer class ---
    public static class Serializer implements RecipeSerializer<AltarRecipe> {
        public static final MapCodec<AltarRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("input1").forGetter(AltarRecipe::input1),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("input2").forGetter(AltarRecipe::input2),
                ItemStack.CODEC.fieldOf("result").forGetter(AltarRecipe::output)
        ).apply(inst, AltarRecipe::new));

        public static final PacketCodec<RegistryByteBuf, AltarRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, AltarRecipe::input1,
                        Ingredient.PACKET_CODEC, AltarRecipe::input2,
                        ItemStack.PACKET_CODEC, AltarRecipe::output,
                        AltarRecipe::new
                );

        @Override
        public MapCodec<AltarRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, AltarRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
