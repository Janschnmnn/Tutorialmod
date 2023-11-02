package net.pernhardiener.tutorialmod.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.pernhardiener.tutorialmod.entity.customgoals.CustomRangedAttackGoal;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ArcherGolemEntity extends HostileEntity implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public int shootCooldown = 80;

    private boolean isShooting;

    public ArcherGolemEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.isShooting = false;
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 16.00)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new CustomRangedAttackGoal<>(this, 50.0f, 0.4f, 60));
        //this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
        //this.goalSelector.add(4, new WanderAroundGoal(this, 0.75f, 1));


        this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }

    @Nullable
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(
                new AnimationController<>(this, "controller", 0, this::predicate)
        );
    }


    public void setShootCondition(boolean value){
        if (isShooting != value) {

            System.out.println("age - isShooting set to: " + value);

            this.isShooting = value;

            System.out.println("age - is now: " + this.isShooting);
        }
    }

    public boolean getShootCondition() {
        if (this.isShooting) {
            System.out.println("age - requesting isShooting: returning true");
        }
        return this.isShooting;
    }


    private<T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        //String animState = tAnimationState.getController().getAnimationState().toString();
        System.out.println("age - " + this.getShootCondition() + "");
        if (this.isShooting) {

            System.out.println("age - animation set to 'shoot'");

            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.archer_golem.shoot", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        if (tAnimationState.isMoving()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.archer_golem.walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        } else {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.archer_golem.idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_SKELETON_STEP;
    }
}
