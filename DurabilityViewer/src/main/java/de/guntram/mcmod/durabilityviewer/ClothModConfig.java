package de.guntram.mcmod.durabilityviewer;


import de.guntram.mcmod.durabilityviewer.cloth.Corner;
import de.guntram.mcmod.durabilityviewer.cloth.WarnMode;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.neoforged.fml.config.ModConfig;

@Config(name = DurabilityViewer.MODID)
public class ClothModConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public   boolean ShowInventoryItemDuration = true;
    public  boolean ArmorAroundHotbar = false;
    public  boolean EffectDuration = true;


    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public  Corner HUDCorner = Corner.BOTTOM_RIGHT;
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public  int HideDamageOverPercent = 100;
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1500)
    public  int SoundBelowDurability = 100;
    @ConfigEntry.BoundedDiscrete(min = 1, max = 100)
    public  int SoundBelowPercent = 10;
    public  boolean ShowAllTrinkets = false;
    public  boolean Percentages = false;


    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public  int PercentToShowDamage = 80;
    public  boolean ShowFreeInventorySlots = true;

//    @ConfigEntry.ColorPicker
//   public  int TooltipColor = 0xFFFFFF;

    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public  WarnMode WarningMode = WarnMode.BOTH;



}