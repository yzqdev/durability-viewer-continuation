package de.guntram.mcmod.durabilityviewer;

import de.guntram.mcmod.durabilityviewer.itemindicator.ShowAllDurability;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

import static de.guntram.mcmod.durabilityviewer.Events.EXAMPLE_MAPPING;

@EventBusSubscriber(modid = DurabilityViewer.MODID ,value = Dist.CLIENT)
public class ModEvent {
    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(EXAMPLE_MAPPING.get());
    }
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onRegisterItemDecorations(final RegisterItemDecorationsEvent event) {

            ShowAllDurability showAllDurability = new ShowAllDurability();
            BuiltInRegistries.ITEM.stream().filter(i -> i.isDamageable(new ItemStack(i))).forEach(item -> event.register(item, showAllDurability));

    }
}
