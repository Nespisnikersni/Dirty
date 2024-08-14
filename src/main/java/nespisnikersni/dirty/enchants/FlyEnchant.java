package nespisnikersni.dirty.enchants;

import nespisnikersni.dirty.items.DirtyItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class FlyEnchant extends Enchantment {
    protected FlyEnchant(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem()==DirtyItems.DRIED_ROOTS_BOOTS;
    }
}
