package de.guntram.mcmod.durabilityviewer;

import com.mojang.blaze3d.platform.InputConstants;
import de.guntram.mcmod.durabilityviewer.client.gui.GuiItemDurability;

import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.util.Lazy;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_H;

@EventBusSubscriber(modid = DurabilityViewer.MODID,value = Dist.CLIENT)
public class Events {
    public static final Lazy<KeyMapping> EXAMPLE_MAPPING = Lazy.of(() -> {  final String category = "key.categories.durabilityviewer";
        return  new KeyMapping("key.durabilityviewer.showhide", InputConstants.Type.KEYSYM, GLFW_KEY_H, category);
    });
    @SubscribeEvent
    public static  void onClientTick(ClientTickEvent.Post event) {
        while (EXAMPLE_MAPPING.get().consumeClick()) {
            GuiItemDurability.toggleVisibility();

        }
    }

}
