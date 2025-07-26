package de.guntram.mcmod.durabilityviewer.client.gui;

import com.google.common.collect.Ordering;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;


import de.guntram.mcmod.durabilityviewer.Config;
import de.guntram.mcmod.durabilityviewer.cloth.HudPosition;
import de.guntram.mcmod.durabilityviewer.cloth.WarnMode;
import de.guntram.mcmod.durabilityviewer.itemindicator.InventorySlotsIndicator;
import de.guntram.mcmod.durabilityviewer.itemindicator.ItemCountIndicator;
import de.guntram.mcmod.durabilityviewer.itemindicator.ItemDamageIndicator;
import de.guntram.mcmod.durabilityviewer.itemindicator.ItemIndicator;
import de.guntram.mcmod.durabilityviewer.sound.ColytraBreakingWarner;
import de.guntram.mcmod.durabilityviewer.sound.ItemBreakingWarner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joml.Matrix4fStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import top.theillusivec4.curios.api.CuriosApi;


public class GuiItemDurability {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Minecraft minecraft;
    private static boolean visible;
    private final Font fontRenderer;
    private final ItemRenderer itemRenderer;

    private long lastWarningTime;
    private ItemStack lastWarningItem;

    private static final int iconWidth = 16;
    private static final int iconHeight = 16;
    private static final int spacing = 2;

    private static boolean haveTrinketsApi = false;
    private static boolean haveTRCore = false;

    private final ItemBreakingWarner mainHandWarner, offHandWarner, helmetWarner, chestWarner, pantsWarner, bootsWarner;
    private final ItemBreakingWarner colytraWarner;
    private ItemBreakingWarner[] trinketWarners;

    public static void toggleVisibility() {
        visible = !visible;
    }

    public GuiItemDurability() {
        minecraft = Minecraft.getInstance();
        fontRenderer = minecraft.font;
        itemRenderer = minecraft.getItemRenderer();
        visible = true;

        mainHandWarner = new ItemBreakingWarner();
        offHandWarner = new ItemBreakingWarner();
        helmetWarner = new ItemBreakingWarner();
        chestWarner = new ItemBreakingWarner();
        pantsWarner = new ItemBreakingWarner();
        bootsWarner = new ItemBreakingWarner();
        colytraWarner = new ColytraBreakingWarner();

        try {
            Class.forName("top.theillusivec4.curios.api.CuriosApi");
            LOGGER.info("Using curios in DurabilityViewer");
            haveTrinketsApi = true;
            trinketWarners = new ItemBreakingWarner[getTrinketSlotCount(minecraft.player)];
            for (int i = 0; i < trinketWarners.length; i++) {
                trinketWarners[i] = new ItemBreakingWarner();
            }
        } catch (ClassNotFoundException ex) {
            LOGGER.info("DurabilityViewer did not find curios API");
            trinketWarners = new ItemBreakingWarner[0];
        }
    }

    private int getInventoryArrowCount() {
        int arrows = 0;
        for (final ItemStack stack : minecraft.player.getInventory().items) {
            if (isArrow(stack)) {
                arrows += stack.getCount();
            }
        }
        return arrows;
    }

    private ItemStack getFirstArrowStack() {
        if (isArrow(minecraft.player.getOffhandItem())) {
            return minecraft.player.getOffhandItem();
        }
        if (isArrow(minecraft.player.getMainHandItem())) {
            return minecraft.player.getMainHandItem();
        }
        int size = minecraft.player.getInventory().getContainerSize();
        for (int i = 0; i < size; ++i) {
            final ItemStack itemstack = minecraft.player.getInventory().getItem(i);
            if (this.isArrow(itemstack)) {
                return itemstack;
            }
        }
        return null;
    }

    private boolean isArrow(final ItemStack stack) {
        return !stack.isEmpty() && stack.getItem() instanceof ArrowItem;
    }

    private static class RenderSize {
        int width;
        int height;

        RenderSize(int w, int h) {
            width = w;
            height = h;
        }
    }

    private enum RenderPos {
        left, over, right;
    }

    public void onRenderGameOverlayPost(GuiGraphics context, float partialTicks) {

        Player player = minecraft.player;
        ItemStack needToWarn = null;

        ItemIndicator mainHand, offHand;
        mainHand = damageOrEnergy(player, EquipmentSlot.MAINHAND);
        offHand = damageOrEnergy(player, EquipmentSlot.OFFHAND);

        ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemIndicator colytra = null;
        /*if (chestItem != null && chestItem.getNbt() != null && chestItem.getNbt().contains("colytra:ElytraUpgrade")) {
            colytra = new ColytraDamageIndicator(chestItem);
        }*/

        ItemIndicator boots = new ItemDamageIndicator(player.getItemBySlot(EquipmentSlot.FEET));
        ItemIndicator leggings = new ItemDamageIndicator(player.getItemBySlot(EquipmentSlot.LEGS));
        ItemIndicator chestplate = new ItemDamageIndicator(chestItem);
        ItemIndicator helmet = new ItemDamageIndicator(player.getItemBySlot(EquipmentSlot.HEAD));
        ItemIndicator arrows = null;
        ItemIndicator invSlots = ( Config.showFreeInventorySlots ? new InventorySlotsIndicator(minecraft.player.getInventory()) : null);

        if (mainHandWarner.checkBreaks(player.getItemBySlot(EquipmentSlot.MAINHAND)))
            needToWarn = player.getItemBySlot(EquipmentSlot.MAINHAND);
        if (needToWarn == null && offHandWarner.checkBreaks(player.getItemBySlot(EquipmentSlot.OFFHAND)))
            needToWarn = player.getItemBySlot(EquipmentSlot.OFFHAND);
        if (needToWarn == null && bootsWarner.checkBreaks(player.getItemBySlot(EquipmentSlot.FEET)))
            needToWarn = player.getItemBySlot(EquipmentSlot.FEET);
        if (needToWarn == null && pantsWarner.checkBreaks(player.getItemBySlot(EquipmentSlot.LEGS)))
            needToWarn = player.getItemBySlot(EquipmentSlot.LEGS);
        if (needToWarn == null && chestWarner.checkBreaks(chestItem)) needToWarn = chestItem;
        if (needToWarn == null && helmetWarner.checkBreaks(player.getItemBySlot(EquipmentSlot.HEAD)))
            needToWarn = player.getItemBySlot(EquipmentSlot.HEAD);
        if (needToWarn == null && colytraWarner.checkBreaks(chestItem)) needToWarn = chestItem;

        ItemIndicator[] trinkets;
        if (haveTrinketsApi) {
            List<ItemStack> equipped = getAllCuriosItems(player);

            trinkets = new ItemIndicator[equipped.size()];
            if (trinkets.length > trinketWarners.length) {
                // Apparently this can happen when joining a server that defines
                // more trinkets than the client?
                trinketWarners = new ItemBreakingWarner[trinkets.length];
                for (int i = 0; i < trinketWarners.length; i++) {
                    trinketWarners[i] = new ItemBreakingWarner();
                }
            }
            LOGGER.debug("know about " + trinkets.length + " curios, invSize is " + equipped.size() + ", have " + trinketWarners.length + " warners");
            for (int i = 0; i < trinkets.length; i++) {
                trinkets[i] = new ItemDamageIndicator(equipped.get(i),  Config.showAllTrinkets );
                if (needToWarn == null && trinketWarners[i].checkBreaks(equipped.get(i))) {
                    needToWarn = equipped.get(i);
                }
                LOGGER.debug("curios position " + i + " has item " + equipped.get(i).getItem().toString());
            }
        } else {
            trinkets = new ItemIndicator[0];
        }

        WarnMode warnMode =  Config.warningMode ;
        if (needToWarn != null) {
            if (warnMode == WarnMode.SOUND || warnMode == WarnMode.BOTH) {
                ItemBreakingWarner.playWarningSound();
            }
            lastWarningTime = System.currentTimeMillis();
            lastWarningItem = needToWarn;
        }

        long timeSinceLastWarning = System.currentTimeMillis() - lastWarningTime;
        if (timeSinceLastWarning < 1000 && (warnMode == WarnMode.VISUAL || warnMode == WarnMode.BOTH)) {
            renderItemBreakingOverlay(context, lastWarningItem, timeSinceLastWarning);
        }

        // Moved this check to down here, in order to play the 
        // warning sound / do the visible 
        if (!visible || minecraft.getDebugOverlay().showDebugScreen()) {
            return;
        }


        if (mainHand.getItemStack().getItem() instanceof ProjectileWeaponItem
                || offHand.getItemStack().getItem() instanceof ProjectileWeaponItem) {
            arrows = new ItemCountIndicator(getFirstArrowStack(), getInventoryArrowCount());
        }

        Window mainWindow = Minecraft.getInstance().getWindow();
        RenderSize armorSize, toolsSize, trinketsSize;
        if ( Config.armorAroundHotbar ) {
            armorSize = new RenderSize(0, 0);
        } else {
            armorSize = this.renderItems(context, 0, 0, false, RenderPos.left, 0, boots, leggings, colytra, chestplate, helmet);
        }
        toolsSize = this.renderItems(context, 0, 0, false, RenderPos.right, 0, invSlots, mainHand, offHand, arrows);
        trinketsSize = this.renderItems(context, 0, 0, false, RenderPos.left, 0, trinkets);

        int totalHeight = (Math.max(toolsSize.height, armorSize.height));
        if (trinketsSize.height > totalHeight) {
            totalHeight = trinketsSize.height;
        }
        if (trinketsSize.width == 0 && trinkets.length > 0 &&  Config.showAllTrinkets) {
            trinketsSize.width = iconWidth + spacing * 2;
        }
        int xposArmor, xposTools, xposTrinkets, ypos;

        HudPosition hudPosition =   Config.hudPosition ;
        switch (hudPosition) {
            case TOP_LEFT -> {
                xposArmor = 5;
                xposTools = 5 + armorSize.width;
                xposTrinkets = 5 + armorSize.width + trinketsSize.width;
                ypos = 5;
            }
            case TOP_RIGHT -> {
                xposArmor = mainWindow.getGuiScaledWidth() - 5 - armorSize.width;
                xposTools = xposArmor - toolsSize.width;
                xposTrinkets = xposTools - trinketsSize.width;
                ypos = 60;   // below buff/debuff effects
            }
            case BOTTOM_LEFT -> {
                xposArmor = 5;
                xposTools = 5 + armorSize.width;
                xposTrinkets = 5 + armorSize.width + trinketsSize.width;
                ypos = mainWindow.getGuiScaledHeight() - 5 - totalHeight;
            }
            case BOTTOM_RIGHT -> {
                xposArmor = mainWindow.getGuiScaledWidth() - 5 - armorSize.width;
                xposTools = mainWindow.getGuiScaledWidth() - 5 - armorSize.width - toolsSize.width;
                xposTrinkets = xposTools - trinketsSize.width;
                ypos = mainWindow.getGuiScaledHeight() - 5 - totalHeight;
            }
            default -> {
                return;
            }
        }

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        if ( Config.armorAroundHotbar) {
            int leftOffset = -120;
            int rightOffset = 100;
            if (!player.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty()) {
                if (minecraft.options.mainHand().get() == HumanoidArm.RIGHT) {
                    leftOffset -= 20;
                } else {
                    rightOffset += 20;
                }
            }
            int helmetTextWidth = fontRenderer.width(helmet.getDisplayValue());
            int chestTextWidth = fontRenderer.width(chestplate.getDisplayValue());
            this.renderItems(context, mainWindow.getGuiScaledWidth() / 2 + leftOffset - helmetTextWidth, mainWindow.getGuiScaledHeight() - iconHeight * 2 - 2, true, RenderPos.left, helmetTextWidth + iconWidth + spacing, helmet);
            this.renderItems(context, mainWindow.getGuiScaledWidth() / 2 + leftOffset - chestTextWidth, mainWindow.getGuiScaledHeight() - iconHeight - 2, true, RenderPos.left, chestTextWidth + iconWidth + spacing, chestplate);
            if (colytra != null) {
                int colytraTextWidth = fontRenderer.width(colytra.getDisplayValue());
                this.renderItems(context, mainWindow.getGuiScaledWidth() / 2 + leftOffset - chestTextWidth - colytraTextWidth - iconWidth, mainWindow.getGuiScaledHeight() - iconHeight - 2, true, RenderPos.left, colytraTextWidth + iconWidth + spacing, colytra);
            }
            this.renderItems(context, mainWindow.getGuiScaledWidth() / 2 + rightOffset, mainWindow.getGuiScaledHeight() - iconHeight * 2 - 2, true, RenderPos.right, armorSize.width, leggings);
            this.renderItems(context, mainWindow.getGuiScaledWidth() / 2 + rightOffset, mainWindow.getGuiScaledHeight() - iconHeight - 2, true, RenderPos.right, armorSize.width, boots);
            if (hudPosition.isRight()) {
                xposTools += armorSize.width;
            } else {
                xposTools -= armorSize.width;
            }
        } else {
            this.renderItems(context, xposArmor, ypos, true, hudPosition.isLeft() ? RenderPos.left : RenderPos.right, armorSize.width, helmet, chestplate, colytra, leggings, boots);
        }
        this.renderItems(context, xposTools, ypos, true, hudPosition.isRight() ? RenderPos.right : RenderPos.left, toolsSize.width, invSlots, mainHand, offHand, arrows);
      if (Config.showAllTrinkets){
          this.renderItems(context, xposTrinkets, ypos, true, hudPosition.isRight() ? RenderPos.right : RenderPos.left, trinketsSize.width, trinkets);
      }
    }

    private ItemIndicator damageOrEnergy(Player player, EquipmentSlot slot) {
        ItemStack stack = player.getItemBySlot(slot);
        if (stack.isDamageableItem()) {
            return new ItemDamageIndicator(stack);
        } else if (haveTRCore) {
            /*if (stack.getItem() instanceof EnergyHolder && stack.getNbt() != null && stack.getNbt().contains("energy", 6)) {
                return new TREnergyIndicator(stack);
            }*/
        }
        return new ItemDamageIndicator(stack);
    }

    private void renderItemBreakingOverlay(GuiGraphics context, ItemStack itemStack, long timeDelta) {
        Window mainWindow = Minecraft.getInstance().getWindow();
        float alpha = 1.0f - ((float) timeDelta / 1000.0f);
        float xWarn = mainWindow.getGuiScaledWidth() / 2f;
        float yWarn = mainWindow.getGuiScaledHeight() / 2f;
        float scale = 5.0f;

        context.fill(0, 0, mainWindow.getGuiScaledWidth(), mainWindow.getGuiScaledHeight(), 0xff0000 + ((int) (alpha * 128) << 24));

        Matrix4fStack stack = RenderSystem.getModelViewStack();
        stack.pushMatrix();
        stack.scale(scale, scale, scale);
        RenderSystem.applyModelViewMatrix();

        context.renderItem(itemStack, (int) ((xWarn) / scale - 8), (int) ((yWarn) / scale - 8));

        stack.popMatrix();
        RenderSystem.applyModelViewMatrix();

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void afterRenderStatusEffects(GuiGraphics context, float partialTicks) {
        if ( Config.effectDuration) {
            // a lot of this is copied from net/minecraft/client/gui/GuiIngame.java
            Window mainWindow = Minecraft.getInstance().getWindow();
            Collection<MobEffectInstance> collection = minecraft.player.getActiveEffects();
            int posGood = 0, posBad = 0;
            for (MobEffectInstance potioneffect : Ordering.natural().reverse().sortedCopy(collection)) {
                if (potioneffect.showIcon()) {
                    MobEffect potion = potioneffect.getEffect().value();
                    int xpos = mainWindow.getGuiScaledWidth();
                    int ypos;
                    if (potion.isBeneficial()) {     // isBeneficial
                        posGood += 25;
                        xpos -= posGood;
                        ypos = 15;
                    } else {
                        posBad += 25;
                        xpos -= posBad;
                        ypos = 41;
                    }
                    int duration = potioneffect.getDuration();
                    String show;
                    if (duration > 1200)
                        show = (duration / 1200) + "m";
                    else
                        show = (duration / 20) + "s";
                    context.drawString(fontRenderer, show, xpos + 2, ypos, ItemIndicator.color_yellow, true);
                }
            }
        }
    }

    private RenderSize renderItems(GuiGraphics context, int xpos, int ypos, boolean reallyDraw, RenderPos numberPos, int maxWidth, ItemIndicator... items) {
        RenderSize result = new RenderSize(0, 0);

        for (ItemIndicator item : items) {
            if (item != null && !item.isEmpty() && item.isItemStackDamageable()) {
                String displayString = item.getDisplayValue();
                int width = fontRenderer.width(displayString);
                if (width > result.width)
                    result.width = width;
                if (reallyDraw) {
                    int color = item.getDisplayColor();
                    context.renderItem(item.getItemStack(), numberPos == RenderPos.left ? xpos + maxWidth - iconWidth - spacing : xpos, ypos + result.height);
                    context.drawString(fontRenderer, displayString, numberPos != RenderPos.right ? xpos : xpos + iconWidth + spacing, (int) (ypos + result.height + fontRenderer.lineHeight / 2f + (numberPos == RenderPos.over ? 10 : 0)), color, true);
                }
                result.height += 16;
            }
        }
        if (result.width != 0)
            result.width += iconWidth + spacing * 2;
        return result;
    }

    public int getTrinketSlotCount(Player player) {

     return    CuriosApi.getPlayerSlots(player).size();

    }

    /**
     * get all equipped curios
     * @param player
     * @return
     */
    public static List<ItemStack> getAllCuriosItems(Player player) {
        List<ItemStack> items = new ArrayList<>();
        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            handler.getCurios().forEach((id, stacksHandler) -> {
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stack = stacksHandler.getStacks().getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        items.add(stack);
                    }
                }
            });
        });
        return items;
    }

}
