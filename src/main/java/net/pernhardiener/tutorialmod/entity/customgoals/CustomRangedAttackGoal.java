package net.pernhardiener.tutorialmod.entity.customgoals;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.pernhardiener.tutorialmod.entity.custom.ArcherGolemEntity;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class CustomRangedAttackGoal<T extends ArcherGolemEntity> extends Goal {
    private final ArcherGolemEntity archerGolemEntity;
    @Nullable
    private LivingEntity target;
    private int seenTargetTicks;
    private final float maxShootRange;
    private final double mobSpeed;
    int cooldown;
    int countdown = 80;

    int animTime;

    public CustomRangedAttackGoal(ArcherGolemEntity archerGolemEntity,  float maxShootRange, double mobSpeed, int cooldown) {
        this.archerGolemEntity = archerGolemEntity;
        this.maxShootRange = maxShootRange;
        this.mobSpeed = mobSpeed;
        this.cooldown = cooldown;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }


    public boolean canStart() {
        LivingEntity livingEntity = this.archerGolemEntity.getTarget();
        if (livingEntity != null && livingEntity.isAlive()) {
            this.target = livingEntity;
            return true;
        } else {
            return false;
        }
    }

    public boolean shouldContinue() {
        return this.canStart() || this.target.isAlive() && !this.archerGolemEntity.getNavigation().isIdle();
    }

    public void stop() {
        this.target = null;
    }

    public void tick() {
        double playerDistance = archerGolemEntity.squaredDistanceTo(target);
        boolean bl = this.archerGolemEntity.getVisibilityCache().canSee(this.target);
        if (bl && playerDistance < maxShootRange) {

            //System.out.println("crag - Player in shooting distance.");

            this.archerGolemEntity.getNavigation().startMovingTo(this.target, mobSpeed);
            this.archerGolemEntity.getLookControl().lookAt(target);
            if (countdown <= 0) {

                System.out.println("crag - initiating condition change to 'true'");

                this.archerGolemEntity.setShootCondition(true);
                countdown = this.cooldown;
                animTime = 20;
            }
        } else {
            this.archerGolemEntity.getNavigation().stop();
        }

        if (animTime > 0) {
            this.archerGolemEntity.getNavigation().stop();
            if (animTime == 10) {

                System.out.println("crag - shooting arrow!");

                shootArrowAtTarget(target);
            }
            animTime--;
        } else {
            this.archerGolemEntity.setShootCondition(false);
        }
        countdown--;
    }
    
    private void shootArrowAtTarget(LivingEntity target) {
        ServerWorld world = (ServerWorld) archerGolemEntity.world;
        ItemStack arrowItem = new ItemStack(Items.ARROW);
        PersistentProjectileEntity arrowEntity = new PersistentProjectileEntity(EntityType.ARROW, archerGolemEntity, world) {
            @Override
            protected ItemStack asItemStack() {
                return arrowItem;
            }

            @Override
            protected void onHit(LivingEntity entity) {
                super.onHit(entity);
                //for adding additional logic to the onHit event.
            }
        };

        Vec3d lookVec = target.getCameraPosVec(1.0F).subtract(archerGolemEntity.getCameraPosVec(1.0F)).normalize();
        arrowEntity.setVelocity(lookVec.x * 2.0D, lookVec.y * 2.0D, lookVec.z * 2.0D);

        arrowEntity.setPos(archerGolemEntity.getX(), archerGolemEntity.getY() + 0.5, archerGolemEntity.getZ());
        world.spawnEntity(arrowEntity);
        world.playSound(null, archerGolemEntity.getX(), archerGolemEntity.getY(), archerGolemEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT,
                archerGolemEntity.getSoundCategory(), 1.0F, 1.0F / (archerGolemEntity.getRandom().nextFloat() * 0.4F + 1.2F) + 0.5F);
    }
}
