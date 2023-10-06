package net.pernhardiener.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pernhardiener.tutorialmod.TutorialMod;

public class ModItemGroup {
    public static ItemGroup MOD_ITEMS;

    public static void registerItemGroups() {
        MOD_ITEMS = FabricItemGroup.builder(new Identifier(TutorialMod.MOD_ID, "mod_items"))
                .displayName(Text.literal("Mod Item Group"))
                .icon(() -> new ItemStack(ModItems.CITRINE)).build();
    }
}
