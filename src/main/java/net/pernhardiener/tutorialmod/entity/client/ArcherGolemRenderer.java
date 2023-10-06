package net.pernhardiener.tutorialmod.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.pernhardiener.tutorialmod.TutorialMod;
import net.pernhardiener.tutorialmod.entity.custom.ArcherGolemEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ArcherGolemRenderer extends GeoEntityRenderer<ArcherGolemEntity> {
    public ArcherGolemRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new ArcherGolemModel());
    }
    /*
    @Override
    public Identifier getTextureLocation(ArcherGolemEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "textures/entity/archer_golem.png");
    }

    @Override
    public void render(ArcherGolemEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
     */
}
