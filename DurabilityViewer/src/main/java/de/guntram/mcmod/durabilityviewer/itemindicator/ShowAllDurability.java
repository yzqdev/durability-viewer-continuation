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

    public boolean render(GuiGraphics guiGraphics, Font font, ItemStack itemStack, int xPosition, int yPosition) {
        if (!itemStack.isEmpty() && itemStack.isDamaged()&& Config.showInventoryItemDurability) {
            PoseStack poseStack = guiGraphics.pose();
            var access = Minecraft.getInstance().level.registryAccess();
            var unbreakEnchant = access.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.UNBREAKING);
            // ItemStack information
            int unbreaking = EnchantmentHelper.getTagEnchantmentLevel(unbreakEnchant, itemStack);
            int maxDamage = itemStack.getMaxDamage();
            int damage = itemStack.getDamageValue();

            // Create string, position, and color
            String string = String.valueOf(((maxDamage - damage) * (unbreaking + 1)));
            int stringWidth = font.width(string);
            int x = ((xPosition + 8) * 2 + 1 + stringWidth / 2 - stringWidth);
            int y = (yPosition * 2) + 18;
            int color = itemStack.getItem().getBarColor(itemStack);

            poseStack.pushPose();
            poseStack.scale(0.5F, 0.5F, 0.5F);
            poseStack.translate(0.0F, 0.0F, 301.0F);
            guiGraphics.drawString(font, string, x, y, color);


            poseStack.popPose();
        }

        return true;
    }

}