package alpheta.abyssal_spectrum.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record AltarInput(ItemStack altarInput, ItemStack crystalInput) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return slot == 0 ? altarInput : crystalInput;
    }

    @Override
    public int getSize() {
        return 2;
    }
}
