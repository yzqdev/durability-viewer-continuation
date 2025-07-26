package de.guntram.mcmod.durabilityviewer.datagen;

import de.guntram.mcmod.durabilityviewer.DurabilityViewer;

/**
 * @author yzqde
 * @date time 2025/7/24 22:56
 * @modified By:
 */
public class LangUtil {

    public static String getConf(String name){
        return DurabilityViewer.MODID+".configuration."+name;
    }
    public static String getText(String name){
        return DurabilityViewer.MODID+".text"+name;
    }

    public static String showInventoryItemDurability = getConf("showInventoryItemDurability");
    public static String armorAroundHotbar = getConf("armorAroundHotbar");
    public static String effectDuration = getConf("effectDuration");
    public static String hudPosition = getConf("hudPosition");
    public static String hideDamageOverPercent = getConf("hideDamageOverPercent");
    public static String soundBelowDurability = getConf("soundBelowDurability");
    public static String soundBelowPercent = getConf("soundBelowPercent");
    public static String showPercentRatherThanDurability = getConf("showPercentRatherThanDurability");
    public static String percentToShowDamageRatherThanDurability = getConf("percentToShowDamageRatherThanDurability");
    public static String showFreeInventorySlots = getConf("showFreeInventorySlots");
    public static String warningMode = getConf("warningMode");
    public static String showAllTrinkets = getConf("showAllTrinkets");
    public static String hudPositionBottomRight= getConf("hudPosition.bottom_right");
    public static String hudPositionBottomLeft= getConf("hudPosition.bottom_left");
    public static String hudPositionTopRight= getConf("hudPosition.top_right");
    public static String hudPositionTopLeft= getConf("hudPosition.top_left");
    public static String warningModeBoth= getConf("warningMode.both");
    public static String warningModeSound= getConf("warningMode.sound");
    public static String warningModeVisual= getConf("warningMode.visual");
    public static String warningModeNone= getConf("warningMode.none");
public static String  keyShowHide= getText("key.showhide");
    public static String keyCategory=LangUtil.getText("key.categories");


}
