/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.guntram.mcmod.durabilityviewer.sound;

import de.guntram.mcmod.durabilityviewer.ClothModConfig;
import de.guntram.mcmod.durabilityviewer.DurabilityViewer;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;


/**
 * @author gbl
 */
public class ItemBreakingWarner {
    private int lastDurability;
    private ItemStack lastStack;
    private static SoundEvent sound;

    public ItemBreakingWarner() {
        lastDurability = 1000;
        lastStack = null;
        ResourceLocation location;

        if (sound == null) {
            location = ResourceLocation.fromNamespaceAndPath(DurabilityViewer.MODID, "tool_breaking");
            sound = SoundEvent.createVariableRangeEvent(location);
        }
    }

    public boolean checkBreaks(ItemStack stack) {
        lastStack = stack;
        if (stack == null || !stack.isDamageableItem())
            return false;
        int newDurability = stack.getMaxDamage() - stack.getDamageValue();
        if (newDurability < lastDurability
                && newDurability < DurabilityViewer.getConfig.SoundBelowDurability
                && newDurability * 100 / DurabilityViewer.getConfig.SoundBelowPercent  < stack.getMaxDamage()) {
            lastDurability = newDurability;
            return true;
        }
        lastDurability = newDurability;
        return false;
    }

    public static void playWarningSound() {
        Minecraft.getInstance().player.playSound(sound, 100, 100);
    }
}
