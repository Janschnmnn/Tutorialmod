package net.pernhardiener.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.pernhardiener.tutorialmod.entity.ModEntities;
import net.pernhardiener.tutorialmod.entity.custom.ArcherGolemEntity;
import net.pernhardiener.tutorialmod.item.ModItemGroup;
import net.pernhardiener.tutorialmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// Very important comment
public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
    public static final Logger LOGGER = LoggerFactory.getLogger("tutorialmod");

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
		FabricDefaultAttributeRegistry.register(ModEntities.ARCHER_GOLEM, ArcherGolemEntity.setAttributes());
	}
}

// continue here:
// https://www.youtube.com/watch?v=7Kg-UvY2DKA