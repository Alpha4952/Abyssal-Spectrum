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

public record ReactorRecipe(Ingredient input1, Ingredient input2, ItemStack output) implements Recipe<ReactorInput> {

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(input1);
        list.add(input2);
        return list;
    }

    @Override
    public boolean matches(ReactorInput input, World world) {
        if (world.isClient()) return false;

        ItemStack stack1 = input.getStackInSlot(0);
        ItemStack stack2 = input.getStackInSlot(1);

        // match either order
        return (input1.test(stack1) && input2.test(stack2)) || (input1.test(stack2) && input2.test(stack1));
    }

    @Override
    public ItemStack craft(ReactorInput input, RegistryWrapper.WrapperLookup lookup) {
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
        return ModRecipes.REACTOR_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.REACTOR_TYPE;
    }

    // --- Serializer class ---
    public static class Serializer implements RecipeSerializer<ReactorRecipe> {
        public static final MapCodec<ReactorRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("input1").forGetter(ReactorRecipe::input1),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("input2").forGetter(ReactorRecipe::input2),
                ItemStack.CODEC.fieldOf("result").forGetter(ReactorRecipe::output)
        ).apply(inst, ReactorRecipe::new));

        public static final PacketCodec<RegistryByteBuf, ReactorRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, ReactorRecipe::input1,
                        Ingredient.PACKET_CODEC, ReactorRecipe::input2,
                        ItemStack.PACKET_CODEC, ReactorRecipe::output,
                        ReactorRecipe::new
                );

        @Override
        public MapCodec<ReactorRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, ReactorRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
