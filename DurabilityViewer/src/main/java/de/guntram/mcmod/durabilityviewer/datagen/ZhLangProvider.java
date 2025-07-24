package de.guntram.mcmod.durabilityviewer.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ZhLangProvider extends LanguageProvider {
    public ZhLangProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override

    protected void addTranslations() {
        // 基本功能
        add(LangUtil.showInventoryItemDurability, "显示背包内物品的耐久");
        add(LangUtil.armorAroundHotbar, "在快捷栏周围显示护甲");
        add(LangUtil.effectDuration, "显示效果持续时间");
        add(LangUtil.showFreeInventorySlots, "显示空闲背包槽位");
        add(LangUtil.showAllTrinkets, "显示饰品耐久");

        // HUD设置
        add(LangUtil.hudPosition, "HUD位置");
        add(LangUtil.hideDamageOverPercent, "当耐久高于此百分比时隐藏HUD");
        add(LangUtil.showPercentRatherThanDurability, "显示百分比而非耐久值");
        add(LangUtil.percentToShowDamageRatherThanDurability, "低于此百分比时显示耐久损伤而非剩余的耐久");

        // 音效设置
        add(LangUtil.soundBelowDurability, "耐久低于此值时播放警告音");
        add(LangUtil.soundBelowPercent, "耐久低于此百分比时播放警告音");

        // 警告模式
        add(LangUtil.warningMode, "警告模式");
        // HUD位置选项
        add(LangUtil.hudPositionBottomRight, "右下角");
        add(LangUtil.hudPositionBottomLeft, "左下角");
        add(LangUtil.hudPositionTopRight, "右上角");
        add(LangUtil.hudPositionTopLeft, "左上角");

// 警告模式选项
        add(LangUtil.warningModeBoth, "视觉和声音效果");
        add(LangUtil.warningModeSound, "仅声音效果");
        add(LangUtil.warningModeVisual, "仅视觉效果");
        add(LangUtil.warningModeNone, "无效果");
        add(LangUtil.keyCategory, "耐久指示器");
        add(LangUtil.keyShowHide, "显示/隐藏 耐久显示");
    }
}
