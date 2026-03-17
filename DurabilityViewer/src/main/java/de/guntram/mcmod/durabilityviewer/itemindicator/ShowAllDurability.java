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
import org.joml.Matrix3x2fStack;

public class ShowAllDurability implements IItemDecorator {

    public boolean render(GuiGraphics guiGraphics, Font font, ItemStack itemStack, int xPosition, int yPosition) {
        if (!itemStack.isEmpty() && itemStack.isDamaged()&& Config.showInventoryItemDurability) {
            Matrix3x2fStack poseStack = guiGraphics.pose();

            int maxDamage = itemStack.getMaxDamage();
            int damage = itemStack.getDamageValue();

            int remaining = maxDamage - damage;
            int factor = 1;
            if (Config.showExpectedHits && Minecraft.getInstance().level != null) {
                try {
                    var access = Minecraft.getInstance().level.registryAccess();
                    var enchantmentRegistry= access.lookupOrThrow(Registries.ENCHANTMENT);
                    var unbreakEnchant=  enchantmentRegistry.getOrThrow(Enchantments.UNBREAKING);
                    // ItemStack information
                    int unbreaking = EnchantmentHelper.getTagEnchantmentLevel(unbreakEnchant, itemStack);


                    int unbreakingLevel = EnchantmentHelper.getTagEnchantmentLevel(unbreakEnchant, itemStack);
                    factor = unbreakingLevel + 1;
                } catch (Exception e) {
                    factor = 1;
                }
            }
            long finalValue = (long) remaining * factor;
            String string = String.valueOf(finalValue);
            int stringWidth = font.width(string);
            int x = ((xPosition + 8) * 2 + 1 + stringWidth / 2 - stringWidth);
            int y = (yPosition * 2) + 18;
            int color = itemStack.getItem().getBarColor(itemStack) | 0xFF000000;

            poseStack.pushMatrix();
            poseStack.scale(0.5F, 0.5F);

            // Draw string
            guiGraphics.drawString(font, string, x, y, color, true);

            poseStack.popMatrix();
        }

        return true;
    }

}