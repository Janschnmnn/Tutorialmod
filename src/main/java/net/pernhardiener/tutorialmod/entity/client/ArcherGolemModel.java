package net.pernhardiener.tutorialmod.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.pernhardiener.tutorialmod.TutorialMod;
import net.pernhardiener.tutorialmod.entity.custom.ArcherGolemEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ArcherGolemModel extends GeoModel {
    @Override
    public Identifier getModelResource(GeoAnimatable animatable) {
        return new Identifier(TutorialMod.MOD_ID, "geo/archer_golem.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoAnimatable animatable) {
        return new Identifier(TutorialMod.MOD_ID, "textures/entity/archer_golem.png");
    }

    @Override
    public Identifier getAnimationResource(GeoAnimatable animatable) {
        return new Identifier(TutorialMod.MOD_ID, "animations/archer_golem.animation.json");
    }

    public void setCustomAnimations(ArcherGolemEntity animatable, long instanceId, AnimationState<ArcherGolemEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
