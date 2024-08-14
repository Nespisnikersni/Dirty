package nespisnikersni.dirty.items;

import nespisnikersni.dirty.effects.DirtyEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class WhiteRootsSwordItem extends SwordItem {
    public WhiteRootsSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this,500);
        Box box = new Box(user.getX() - 5, user.getY() - 5, user.getZ() - 5, user.getX() + 5, user.getY() + 5, user.getZ() + 5);
        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class,box,livingEntity -> {return livingEntity!=user;});
        for(LivingEntity entity:entities){
            entity.addStatusEffect(new StatusEffectInstance(DirtyEffects.STUN,100));
        }
        return super.use(world, user, hand);
    }
}
