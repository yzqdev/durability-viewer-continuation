package de.guntram.mcmod.durabilityviewer.datagen;

import de.guntram.mcmod.durabilityviewer.DurabilityViewer;

/**
 * @author yzqde
 * @date time 2025/7/24 22:56
 * @modified By:
 */
public class LangUtil {


    public static String getConf(String name) {
        return DurabilityViewer.MODID + ".configuration." + name;
    }

    public static String getConfTooltip(String name) {
        return DurabilityViewer.MODID + ".configuration." + name + ".tooltip";
    }

    public static String getText(String name) {
        return DurabilityViewer.MODID + ".text" + name;
    }

    public static String showInventoryItemDurability = getConf("showInventoryItemDurability");
    public static String showInventoryItemDurabilityTooltip = getConfTooltip("showInventoryItemDurability");
    public static String armorAroundHotbar = getConf("armorAroundHotbar");
    public static String armorAroundHotbarTooltip = getConfTooltip("armorAroundHotbar");
    public static String effectDuration = getConf("effectDuration");
    public static String effectDurationTooltip = getConfTooltip("effectDuration");
    public static String hudPosition = getConf("hudPosition");
    public static String hudPositionTooltip = getConfTooltip("hudPosition");
    public static String hideDamageOverPercent = getConf("hideDamageOverPercent");
    public static String hideDamageOverPercentTooltip = getConfTooltip("hideDamageOverPercent");
    public static String soundBelowDurability = getConf("soundBelowDurability");
    public static String soundBelowDurabilityTooltip = getConfTooltip("soundBelowDurability");
    public static String soundBelowPercent = getConf("soundBelowPercent");
    public static String soundBelowPercentTooltip = getConfTooltip("soundBelowPercent");
    public static String showPercentRatherThanDurability = getConf("showPercentRatherThanDurability");
    public static String showPercentRatherThanDurabilityTooltip = getConfTooltip("showPercentRatherThanDurability");
    public static String percentToShowDamageRatherThanDurability = getConf("percentToShowDamageRatherThanDurability");
    public static String percentToShowDamageRatherThanDurabilityTooltip = getConfTooltip(
            "percentToShowDamageRatherThanDurability");
    public static String showFreeInventorySlots = getConf("showFreeInventorySlots");
    public static String showFreeInventorySlotsTooltip = getConfTooltip("showFreeInventorySlots");
    public static String warningMode = getConf("warningMode");
    public static String warningModeTooltip = getConfTooltip("warningMode");
    public static String showAllTrinkets = getConf("showAllTrinkets");
    public static String showAllTrinketsTooltip = getConfTooltip("showAllTrinkets");
    public static String hudPositionBottomRight = getConf("hudPosition.bottom_right");
    public static String hudPositionBottomLeft = getConf("hudPosition.bottom_left");
    public static String hudPositionTopRight = getConf("hudPosition.top_right");
    public static String hudPositionTopLeft = getConf("hudPosition.top_left");
    public static String warningModeBoth = getConf("warningMode.both");
    public static String warningModeSound = getConf("warningMode.sound");
    public static String warningModeVisual = getConf("warningMode.visual");
    public static String warningModeNone = getConf("warningMode.none");
    public static String showExpectedHits = getConf("showExpectedHits");
    public static String showExpectedHitsTooltip = getConf("showExpectedHits.tooltip");
    public static String keyShowHide = getText("key.showhide");
    public static String keyCategory = LangUtil.getText("key.categories");


}
