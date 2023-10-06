package net.pernhardiener.tutorialmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.pernhardiener.tutorialmod.TutorialMod;
import net.pernhardiener.tutorialmod.entity.custom.ArcherGolemEntity;


public class ModEntities {
    public static final EntityType<ArcherGolemEntity> ARCHER_GOLEM = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(TutorialMod.MOD_ID, "archer_golem"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ArcherGolemEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f,1.75f)).build());
}
