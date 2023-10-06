package net.pernhardiener.tutorialmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.pernhardiener.tutorialmod.entity.ModEntities;
import net.pernhardiener.tutorialmod.entity.client.ArcherGolemRenderer;

public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.ARCHER_GOLEM, ArcherGolemRenderer::new);
    }
}
