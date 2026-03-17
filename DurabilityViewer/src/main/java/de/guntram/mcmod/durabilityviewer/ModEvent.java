package de.guntram.mcmod.durabilityviewer;

import com.mojang.blaze3d.platform.InputConstants;
import de.guntram.mcmod.durabilityviewer.client.gui.GuiItemDurability;
import de.guntram.mcmod.durabilityviewer.datagen.LangUtil;
import de.guntram.mcmod.durabilityviewer.itemindicator.ShowAllDurability;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_H;


@EventBusSubscriber(modid = DurabilityViewer.MODID, value = Dist.CLIENT)
public class ModEvent {
    public static final KeyMapping.Category DURABILITY_CATEGORY = new KeyMapping.Category(
            Identifier.fromNamespaceAndPath(DurabilityViewer.MODID, "durability"));

    public static final Lazy<KeyMapping> SHOW_DURABILITY_KEY = Lazy.of(() -> {
        final String category = LangUtil.keyDurabilityCategory;
        return new KeyMapping(LangUtil.keyShowHide, InputConstants.Type.KEYSYM, GLFW_KEY_H, DURABILITY_CATEGORY);
    });

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.registerCategory(DURABILITY_CATEGORY);

        event.register(SHOW_DURABILITY_KEY.get());
    }

    @SubscribeEvent
    public static void onRegisterItemDecorations(final RegisterItemDecorationsEvent event) {

        ShowAllDurability showAllDurability = new ShowAllDurability();
        BuiltInRegistries.ITEM.forEach(item -> {

            event.register(item, showAllDurability);
        });


    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        while (SHOW_DURABILITY_KEY.get()
                .consumeClick()) {
            GuiItemDurability.toggleVisibility();

        }
    }

}
