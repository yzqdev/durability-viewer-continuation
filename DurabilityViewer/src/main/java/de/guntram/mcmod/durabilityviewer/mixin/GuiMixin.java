package de.guntram.mcmod.durabilityviewer.mixin;

import de.guntram.mcmod.durabilityviewer.client.gui.GuiItemDurability;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {

    @Unique
    private static GuiItemDurability kafka$gui;

    @Inject(method = "extractRenderState", at = @At("RETURN"))
    private void afterRenderStatusEffects(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (kafka$gui == null)
            kafka$gui = new GuiItemDurability();
        kafka$gui.afterRenderStatusEffects(graphics, 0);
    }

    @Inject(method = "extractItemHotbar", at = @At(value = "RETURN", args = {"log=false"}))
    private void beforeRenderDebugScreen(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (kafka$gui == null) {
            kafka$gui = new GuiItemDurability();
        }
        kafka$gui.onRenderGameOverlayPost(graphics, 0);
    }
}
