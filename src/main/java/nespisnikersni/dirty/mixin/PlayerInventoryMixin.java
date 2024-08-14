package nespisnikersni.dirty.mixin;

import nespisnikersni.dirty.Dirty;
import nespisnikersni.dirty.enchants.DirtyEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {

    private ItemStack previousBoots = ItemStack.EMPTY;

    @Inject(method = "updateItems", at = @At("HEAD"))
    public void onUpdateItems(CallbackInfo ci) {
        PlayerInventory inventory = (PlayerInventory) (Object) this;
        PlayerEntity player = inventory.player;
        ItemStack currentBoots = inventory.getArmorStack(0);

        if (!ItemStack.areEqual(previousBoots, currentBoots)&&!player.getWorld().isClient) {
            if (!previousBoots.isEmpty() && EnchantmentHelper.getLevel(DirtyEnchants.FLY, previousBoots) > 0) {
                player.getAbilities().allowFlying = false;
                player.getAbilities().flying = false;
                player.sendAbilitiesUpdate();
            }
            previousBoots = currentBoots.copy();
        }
    }
}
