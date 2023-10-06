package net.pernhardiener.tutorialmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.pernhardiener.tutorialmod.TutorialMod;

public class ModItems {

    public static final Item CITRINE = registerItem("citrine",
            new Item(new FabricItemSettings()));

    public static final Item NEW_ITEM = registerItem("new_item",
            new Item(new FabricItemSettings()));

    public static void addItemsToItemGroup() {
        addToItemGroup(ItemGroups.INGREDIENTS, CITRINE);
        addToItemGroup(ModItemGroup.MOD_ITEMS, NEW_ITEM);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(TutorialMod.MOD_ID, name),item);
    }

    private static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering mod items for " + TutorialMod.MOD_ID);

        addItemsToItemGroup();
    }
}
