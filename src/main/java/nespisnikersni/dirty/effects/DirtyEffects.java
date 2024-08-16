package nespisnikersni.dirty.effects;

import nespisnikersni.dirty.Dirty;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DirtyEffects {
    public static final StatusEffect STUN = regEffect("stun",new StunEffect(StatusEffectCategory.HARMFUL,0xF3F620));

    private static StatusEffect regEffect(String id,StatusEffect effect){
        return Registry.register(Registries.STATUS_EFFECT,new Identifier(Dirty.MOD_ID,id),effect);
    }
    public static void register(){}
}
