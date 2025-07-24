package de.guntram.mcmod.durabilityviewer.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class EnLangProvider extends LanguageProvider {
    public EnLangProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        add(LangUtil.showInventoryItemDurability, "Show inventory item durability");
        add(LangUtil.armorAroundHotbar, "Display armor around hotbar");
        add(LangUtil.effectDuration, "Show effect duration");
        add(LangUtil.showFreeInventorySlots, "Show empty inventory slots");
        add(LangUtil.showAllTrinkets, "Show trinkets durability");

// HUD Settings
        add(LangUtil.hudPosition, "HUD position");
        add(LangUtil.hideDamageOverPercent, "Hide HUD when durability is above this percentage");
        add(LangUtil.showPercentRatherThanDurability, "Show percentage instead of durability value");
        add(LangUtil.percentToShowDamageRatherThanDurability, "Show durability damage instead of remaining durability");

// Sound Settings
        add(LangUtil.soundBelowDurability, "Play warning sound when durability falls below this value");
        add(LangUtil.soundBelowPercent, "Play warning sound when durability falls below this percentage");

// Warning Mode
        add(LangUtil.warningMode, "Warning mode");
        add(LangUtil.hudPositionBottomRight, "Bottom Right");
        add(LangUtil.hudPositionBottomLeft, "Bottom Left");
        add(LangUtil.hudPositionTopRight, "Top Right");
        add(LangUtil.hudPositionTopLeft, "Top Left");

// Warning Mode Options
        add(LangUtil.warningModeBoth, "Both Visual and Sound");
        add(LangUtil.warningModeSound, "Sound Only");
        add(LangUtil.warningModeVisual, "Visual Only");
        add(LangUtil.warningModeNone, "No Warnings");
    }
}
