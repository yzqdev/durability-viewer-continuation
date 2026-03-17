package de.guntram.mcmod.durabilityviewer.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class EnLangProvider extends LanguageProvider {
    public EnLangProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        add(LangUtil.showInventoryItemDurability, "Show Inventory Durability");
        add(LangUtil.showInventoryItemDurabilityTooltip, "Displays the remaining durability value directly on the inventory icons.");


        add(LangUtil.armorAroundHotbar, "Armor Around Hotbar");
        add(LangUtil.armorAroundHotbarTooltip, "Renders the icons and durability of currently equipped armor on both sides of the hotbar.");


        add(LangUtil.effectDuration, "Show Status Effect Duration");
        add(LangUtil.effectDurationTooltip, "Displays the precise remaining time below the status effect icons in the top right corner.");


        add(LangUtil.showFreeInventorySlots, "Show Free Inventory Slots");
        add(LangUtil.showFreeInventorySlotsTooltip, "Displays the number of empty slots currently remaining in your inventory in real-time.");


        add(LangUtil.showAllTrinkets, "Show All Trinket Durability");
        add(LangUtil.showAllTrinketsTooltip, "When enabled, displays durability for items in Trinkets/Curios mod slots in addition to the main hand and armor.");

        add(LangUtil.showExpectedHits, "Show Expected Uses");
        add(LangUtil.showExpectedHitsTooltip, "On: Shows the average number of uses considering Unbreaking enchantments; Off: Shows raw durability values.");

        add(LangUtil.hudPosition, "HUD Position");
        add(LangUtil.hudPositionTooltip, "Sets the rendering position of the durability information on the screen (e.g., Top Left, Top Right, Center, etc.).");

        add(LangUtil.hideDamageOverPercent, "Auto-Hide at High Durability");
        add(LangUtil.hideDamageOverPercentTooltip, "Hides the durability info when an item's durability percentage is above this value (set to 100 to always show).");

        add(LangUtil.showPercentRatherThanDurability, "Prefer Percentage");
        add(LangUtil.showPercentRatherThanDurabilityTooltip, "On: Displays remaining percentage (e.g., 80.5%); Off: Displays specific values (e.g., 1250).");

        add(LangUtil.percentToShowDamageRatherThanDurability, "Damage Value Threshold %");
        add(LangUtil.percentToShowDamageRatherThanDurabilityTooltip, "When an item is heavily damaged (below this %), the display switches to 'amount of durability lost' to help track repair progress.");


        add(LangUtil.soundBelowDurability, "Low Durability Sound Threshold");
        add(LangUtil.soundBelowDurabilityTooltip, "Plays a warning sound when an item's remaining durability falls below this value.");

        add(LangUtil.soundBelowPercent, "Low Durability Sound Percentage");
        add(LangUtil.soundBelowPercentTooltip, "Plays a warning sound when an item's remaining percentage falls below this value.");


        add(LangUtil.warningMode, "Warning Mode");
        add(LangUtil.warningModeTooltip, "Choose how to be notified when durability is low (e.g., sound only, visual flash only, or both).");

        add(LangUtil.hudPositionBottomRight, "Bottom Right");
        add(LangUtil.hudPositionBottomLeft, "Bottom Left");
        add(LangUtil.hudPositionTopRight, "Top Right");
        add(LangUtil.hudPositionTopLeft, "Top Left");


        add(LangUtil.warningModeBoth, "Visual & Sound");
        add(LangUtil.warningModeSound, "Sound Only");
        add(LangUtil.warningModeVisual, "Visual Only");
        add(LangUtil.warningModeNone, "None");

        add(LangUtil.keyCategory, "Durability Indicator");
        add(LangUtil.keyShowHide, "Toggle Durability Display");
    }
}
