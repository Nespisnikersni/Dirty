package nespisnikersni.dirty.mixin;

import nespisnikersni.dirty.effects.DirtyEffects;
import nespisnikersni.dirty.effects.StunEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "move",at = @At("HEAD"),cancellable = true)
    private void move(MovementType movementType, Vec3d movement, CallbackInfo ci){
        Entity entity = (Entity) (Object) this;
        if(entity instanceof LivingEntity livingEntity&&livingEntity.hasStatusEffect(DirtyEffects.STUN)){
            ci.cancel();
        }
    }
}
