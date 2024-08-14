package nespisnikersni.dirty.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DriedRootsArmorItem extends ArmorItem{
    public DriedRootsArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        PlayerEntity player=(PlayerEntity) entity;
        if (player.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof DriedRootsArmorItem &&
                player.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof DriedRootsArmorItem &&
                player.getEquippedStack(EquipmentSlot.LEGS).getItem() instanceof DriedRootsArmorItem &&
                player.getEquippedStack(EquipmentSlot.FEET).getItem() instanceof DriedRootsArmorItem) {
           player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 0));
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
