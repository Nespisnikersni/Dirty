package nespisnikersni.dirty.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class WhiteRootsSwordItem extends SwordItem {
    public WhiteRootsSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this,300);
        Box box = new Box(user.getX() - 5, user.getY() - 5, user.getZ() - 5, user.getX() + 5, user.getY() + 5, user.getZ() + 5);
        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class,box,livingEntity -> {return livingEntity!=user;});
        for(LivingEntity entity:entities){
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,100,5));
        }
        world.playSound(null,user.getBlockPos(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS);
        if (world.isClient) {
            double radius = 2.5;
            int particleCount = 100;

            for (int i = 0; i < particleCount; i++) {
                double angle = 2 * Math.PI * i / particleCount;
                double offsetX = MathHelper.cos((float) angle) * radius;
                double offsetZ = MathHelper.sin((float) angle) * radius;
                double x = user.getX() + offsetX;
                double y = user.getY() + 0.5;
                double z = user.getZ() + offsetZ;
                double velocityX = MathHelper.cos((float) angle) * 0.5;
                double velocityZ = MathHelper.sin((float) angle) * 0.5;
                world.addParticle(ParticleTypes.FIREWORK, x, y, z, velocityX, 0, velocityZ);
            }
        }
        return super.use(world, user, hand);
    }
}
