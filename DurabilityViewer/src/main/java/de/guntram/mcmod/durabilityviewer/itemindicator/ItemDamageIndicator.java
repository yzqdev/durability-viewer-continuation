package de.guntram.mcmod.durabilityviewer.itemindicator;

 

import de.guntram.mcmod.durabilityviewer.Config;
import de.guntram.mcmod.durabilityviewer.DurabilityViewer;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

public class ItemDamageIndicator implements ItemIndicator {

    final ItemStack stack;
    boolean alwaysAssumeDamageable;

    public ItemDamageIndicator(ItemStack stack) {
        this(stack, false);
    }

    public ItemDamageIndicator(ItemStack stack, boolean alwaysDamageable) {
        this.stack = stack;
        this.alwaysAssumeDamageable = alwaysDamageable;
    }

    @Override
    public String getDisplayValue() {
        if (!(stack.isDamageableItem())) {
            return "";
        }
        return calculateDisplayValue(stack );
    }

    public static String calculateDisplayValue(ItemStack stack) {
        int max = stack.getMaxDamage();
        int dam = stack.getDamageValue();

        // 1. 获取耐久等级 (Unbreaking)
        int unbreaking = 0;
        var level = Minecraft.getInstance().level;

        if (level != null) {
            unbreaking = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT)
                    .get(Enchantments.UNBREAKING)
                    .map(enchant -> EnchantmentHelper.getItemEnchantmentLevel(enchant, stack))
                    .orElse(0);
        }


        int factor = Config.showExpectedHits ? (unbreaking + 1) : 1;

        // 剩余原生耐久点数
        int remainingPoints = max - dam;


        boolean showLossValue = remainingPoints > (max * Config.percentToShowDamageRatherThanDurability / 100);


        if (Config.showPercentRatherThanDurability) {
            double percent = showLossValue ? (-(double)dam * 100.0 / max) : ((double)remainingPoints * 100.0 / max);
            return String.format("%.1f%%", percent);
        }


        long shownValue = showLossValue ? (-(long)dam * factor) : ((long)remainingPoints * factor);
        return String.valueOf(shownValue);
    }

    @Override
    public int getDisplayColor() {
        int max = stack.getMaxDamage();
        int cur = stack.getDamageValue();
        return calculateDisplayColor(max, cur);
    }

    public static int calculateDisplayColor(int max, int cur) {
        if (cur < max / 5)
            return color_green;
        if (cur > max * 9 / 10 && cur > max - 100)
            return color_red;
        if (cur > max * 4 / 5 && cur > max - 200)
            return color_yellow;
        return color_white;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty() || (stack.getMaxDamage() - stack.getDamageValue() > stack.getMaxDamage() *  Config.hideDamageOverPercent / 100);
    }

    @Override
    public boolean isItemStackDamageable() {
        return alwaysAssumeDamageable || stack.isDamageableItem();
    }

    @Override
    public ItemStack getItemStack() {
        return stack;
    }
}
