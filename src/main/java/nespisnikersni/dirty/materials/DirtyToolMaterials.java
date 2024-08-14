package nespisnikersni.dirty.materials;

import java.util.function.Supplier;

import nespisnikersni.dirty.items.DirtyItems;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

public enum DirtyToolMaterials implements ToolMaterial {
    DRIED_ROOTS(4, 1000, 9.0F, 0.0F, 15, () -> {
        return Ingredient.ofItems(DirtyItems.DRIED_ROOTS);
    }),
    WHITE_ROOTS(5, 6500, 15.0F, 0.0F, 15, () -> {
        return Ingredient.ofItems(DirtyItems.WHITE_ROOTS);
    });

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    private DirtyToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy(repairIngredient);
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

    public int getMiningLevel() {
        return this.miningLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }
}
