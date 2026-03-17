package de.guntram.mcmod.durabilityviewer.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ZhLangProvider extends LanguageProvider {
    public ZhLangProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override

    protected void addTranslations() {


        add(LangUtil.showInventoryItemDurability, "显示背包内物品耐久");
        add(LangUtil.showInventoryItemDurabilityTooltip, "在物品栏图标上直接显示剩余耐久数值。");


        add(LangUtil.armorAroundHotbar, "在快捷栏周围显示护甲");
        add(LangUtil.armorAroundHotbarTooltip, "在快捷栏左右两侧渲染当前穿着装备的图标及耐久。");


        add(LangUtil.effectDuration, "显示状态效果时间");
        add(LangUtil.effectDurationTooltip, "在屏幕右上角的状态效果图标下方显示精确的剩余时间。");


        add(LangUtil.showFreeInventorySlots, "显示背包空闲槽位");
        add(LangUtil.showFreeInventorySlotsTooltip, "在屏幕上实时显示当前背包中剩余的空空格数量。");


        add(LangUtil.showAllTrinkets, "显示所有饰品耐久");
        add(LangUtil.showAllTrinketsTooltip, "开启后，除了主手和护甲，还会显示 Trinkets/Curios 等模组槽位中的物品耐久。");
        add(LangUtil.showExpectedHits, "显示期望使用次数");
        add(LangUtil.showExpectedHitsTooltip, "开启：显示考虑耐久附魔后的平均可使用次数；关闭：显示原始耐久数值。");

        add(LangUtil.hudPosition, "HUD显示位置");
        add(LangUtil.hudPositionTooltip, "设置耐久信息在屏幕上的渲染位置（如左上、右上、中心等）。");

        add(LangUtil.hideDamageOverPercent, "高耐久时自动隐藏");
        add(LangUtil.hideDamageOverPercentTooltip, "当物品耐久百分比高于此值时，不再显示该物品的耐久信息（设为100则始终显示）。");

        add(LangUtil.showPercentRatherThanDurability, "优先显示百分比");
        add(LangUtil.showPercentRatherThanDurabilityTooltip, "开启：显示剩余百分比（如 80.5%）；关闭：显示具体数值（如 1250）。");

        add(LangUtil.percentToShowDamageRatherThanDurability, "显示损耗值的临界百分比");
        add(LangUtil.percentToShowDamageRatherThanDurabilityTooltip, "当物品受损严重（低于此百分比）时，将数值切换为显示“已消耗了多少点”，方便查看修理进度。");


        add(LangUtil.soundBelowDurability, "低耐久警告音触发值");
        add(LangUtil.soundBelowDurabilityTooltip, "当物品的剩余耐久数值低于此设定值时，播放警告音效。");

        add(LangUtil.soundBelowPercent, "低耐久警告百分比");
        add(LangUtil.soundBelowPercentTooltip, "当物品的剩余百分比低于此设定值时，播放警告音效。");


        add(LangUtil.warningMode, "警告提醒模式");
        add(LangUtil.warningModeTooltip, "选择低耐久时的提醒方式（例如：仅音效、仅视觉闪烁、或两者兼有）。");

        add(LangUtil.hudPositionBottomRight, "右下角");
        add(LangUtil.hudPositionBottomLeft, "左下角");
        add(LangUtil.hudPositionTopRight, "右上角");
        add(LangUtil.hudPositionTopLeft, "左上角");


        add(LangUtil.warningModeBoth, "视觉和声音效果");
        add(LangUtil.warningModeSound, "仅声音效果");
        add(LangUtil.warningModeVisual, "仅视觉效果");
        add(LangUtil.warningModeNone, "无效果");
        add(LangUtil.keyCategory, "耐久指示器");
        add(LangUtil.keyShowHide, "显示/隐藏 耐久显示");
    }
}
