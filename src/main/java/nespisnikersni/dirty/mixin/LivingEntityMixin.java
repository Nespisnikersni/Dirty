package nespisnikersni.dirty.mixin;

import nespisnikersni.dirty.effects.DirtyEffects;
import nespisnikersni.dirty.items.DirtyItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
    private void cancelFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = ((LivingEntity) (Object) this);
        if(entity instanceof ServerPlayerEntity player) {
            if(haveWRArmor(player)) {
                if(!player.isCreative()) {
                    player.getInventory().getArmorStack(0).damage((int) fallDistance/4,player.getRandom(), player);
                    player.getInventory().getArmorStack(1).damage((int) fallDistance/4,player.getRandom(), player);
                    player.getInventory().getArmorStack(2).damage((int) fallDistance/4,player.getRandom(), player);
                    player.getInventory().getArmorStack(3).damage((int) fallDistance/4,player.getRandom(), player);
                }
                cir.setReturnValue(false);
            }
        }
    }
    @Inject(method = "canTarget(Lnet/minecraft/entity/LivingEntity;)Z", at = @At("HEAD"),cancellable = true)
    private void canTarget(LivingEntity target, CallbackInfoReturnable<Boolean> cir){
        LivingEntity entity = ((LivingEntity) (Object) this);
        if(entity.hasStatusEffect(DirtyEffects.STUN)){
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "canHit", at = @At("HEAD"),cancellable = true)
    private void canHit(CallbackInfoReturnable<Boolean> cir){
        LivingEntity entity = ((LivingEntity) (Object) this);
        if(entity.hasStatusEffect(DirtyEffects.STUN)){
            cir.setReturnValue(false);
        }
    }
    @Unique
    private static boolean haveWRArmor(PlayerEntity player){
        return player.getInventory().getArmorStack(0).getItem()==DirtyItems.WHITE_ROOTS_BOOTS&&player.getInventory().getArmorStack(1).getItem()==DirtyItems.WHITE_ROOTS_LEGGINGS
                &&player.getInventory().getArmorStack(2).getItem()==DirtyItems.WHITE_ROOTS_CHESTPLATE&&player.getInventory().getArmorStack(3).getItem()==DirtyItems.WHITE_ROOTS_HELMET;
    }
}
