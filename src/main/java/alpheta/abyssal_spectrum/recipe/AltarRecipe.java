package alpheta.abyssal_spectrum.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record AltarRecipe(Ingredient altar, Ingredient crystal, ItemStack output, int level) implements Recipe<AltarInput> {

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(altar);
        list.add(crystal);
        return list;
    }

    public boolean matches(AltarInput AltarInput, World world) {
        if (world.isClient()) return false;

        ItemStack AltarStack = AltarInput.getStackInSlot(0);
        ItemStack CrystalStack = AltarInput.getStackInSlot(1);

        return (altar.test(AltarStack) && crystal.test(CrystalStack));
    }

    @Override
    public ItemStack craft(AltarInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.ALTAR_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.ALTAR_TYPE;
    }

    public int getLevel() {
        return level;
    }

    // --- Serializer class ---
    public static class Serializer implements RecipeSerializer<AltarRecipe> {
        public static final MapCodec<AltarRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("altar").forGetter(AltarRecipe::altar),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("crystal").forGetter(AltarRecipe::crystal),
                ItemStack.CODEC.fieldOf("result").forGetter(AltarRecipe::output),
                Codec.INT.fieldOf("level").forGetter(AltarRecipe::level)
        ).apply(inst, AltarRecipe::new));

        public static final PacketCodec<RegistryByteBuf, AltarRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, AltarRecipe::altar,
                        Ingredient.PACKET_CODEC, AltarRecipe::crystal,
                        ItemStack.PACKET_CODEC, AltarRecipe::output,
                        PacketCodecs.VAR_INT, AltarRecipe::level,
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
