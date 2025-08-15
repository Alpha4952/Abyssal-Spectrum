package alpheta.abyssal_spectrum.item;

import alpheta.abyssal_spectrum.util.ModTags;
import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;

import java.util.Objects;
import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    ABYSSAL_STEEL(ModTags.Blocks.INCORRECT_FOR_ABYSSAL_STEEL_TOOL, 4096, 10.0F, 5.0F, 0, () -> Ingredient.ofItems(new ItemConvertible[]{ModItems.abyssal_steel_ingot})),
    ABYSSALITE(ModTags.Blocks.INCORRECT_FOR_ABYSSALITE_TOOL, 6144, 11.0F, 6.0F, 0, () -> Ingredient.ofItems(new ItemConvertible[]{ModItems.ebonite_ingot}));

    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    private ModToolMaterials(
            final TagKey<Block> inverseTag,
            final int itemDurability, final float miningSpeed,
            final float attackDamage, final int enchantability,
            final Supplier<Ingredient> repairIngredient
    ) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        Objects.requireNonNull(repairIngredient);
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    public int getDurability() {
        return this.itemDurability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }
}
