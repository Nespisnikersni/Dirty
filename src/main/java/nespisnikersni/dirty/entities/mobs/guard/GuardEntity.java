package nespisnikersni.dirty.entities.mobs.guard;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.function.Predicate;

public class GuardEntity extends HostileEntity{
    private int ticksExisted = 0;
    private PlayerEntity player;

    public GuardEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
        this.initGoals();
    }
    public PlayerEntity getPlayer(){
        return this.player;
    }

    public static DefaultAttributeContainer.Builder createGuardAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10f)
                .add(EntityAttributes.GENERIC_ARMOR,10);
    }
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 3.0F));
        this.goalSelector.add(2,new LookAroundGoal(this));
        this.goalSelector.add(3,new WanderAroundGoal(this,0.4));
        this.goalSelector.add(4,new WanderAroundFarGoal(this,1));
        this.goalSelector.add(6,new MeleeAttackGoal(this,0.5,false));
        this.goalSelector.add(5, new ActiveTargetGoal<>(this, MobEntity.class, true,livingEntity -> {
            return livingEntity!=player&& !(livingEntity instanceof GuardEntity);
        }));
        super.initGoals();
    }

    @Override
    protected void mobTick() {
        System.out.println(this.getTarget());
        ticksExisted++;
        if (ticksExisted >= 3*20*60) {
            this.remove(RemovalReason.DISCARDED);
        }
        super.mobTick();
    }


}

