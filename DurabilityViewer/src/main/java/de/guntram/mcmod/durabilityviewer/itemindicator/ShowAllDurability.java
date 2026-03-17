package de.guntram.mcmod.durabilityviewer.itemindicator;

import com.mojang.blaze3d.vertex.PoseStack;
import de.guntram.mcmod.durabilityviewer.Config;
import de.guntram.mcmod.durabilityviewer.DurabilityViewer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.client.IItemDecorator;
public class ShowAllDurability implements IItemDecorator {

    @Override
    public boolean render(GuiGraphics guiGraphics, Font font, ItemStack itemStack, int xPosition, int yPosition) {
        // 基础显示开关检查
        if (itemStack.isEmpty() || !itemStack.isDamaged() || !Config.showInventoryItemDurability ) {
            return false;
        }


        int maxDamage = itemStack.getMaxDamage();
        int damage = itemStack.getDamageValue();
        int remaining = maxDamage - damage;


        int factor = 1;
        if (Config.showExpectedHits && Minecraft.getInstance().level != null) {
            try {
                var access = Minecraft.getInstance().level.registryAccess();
                var unbreakEnchant = access.registryOrThrow(Registries.ENCHANTMENT)
                        .getHolderOrThrow(Enchantments.UNBREAKING);
                int unbreakingLevel = EnchantmentHelper.getItemEnchantmentLevel(unbreakEnchant, itemStack);
                factor = unbreakingLevel + 1;
            } catch (Exception e) {

                factor = 1;
            }
        }

        // 3. 计算最终显示的字符串
        long finalValue = (long) remaining * factor;
        String string = String.valueOf(finalValue);

        // 4. 渲染逻辑
        PoseStack poseStack = guiGraphics.pose();
        int stringWidth = font.width(string);

        int x = ((xPosition + 8) * 2 + 1 + stringWidth / 2 - stringWidth);
        int y = (yPosition * 2) + 18;
        int color = itemStack.getItem().getBarColor(itemStack);

        poseStack.pushPose();
        poseStack.scale(0.5F, 0.5F, 0.5F);
        poseStack.translate(0.0F, 0.0F, 301.0F);

        // 绘制文字（带阴影更清晰）
        guiGraphics.drawString(font, string, x, y, color );

        poseStack.popPose();

        return true;
    }
}