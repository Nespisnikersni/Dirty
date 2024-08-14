package nespisnikersni.dirty.enchants;

import nespisnikersni.dirty.items.DirtyItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class BoerEnchant extends Enchantment {
    public BoerEnchant(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem()!= DirtyItems.WHITE_ROOTS_PICKAXE;
    }
}
