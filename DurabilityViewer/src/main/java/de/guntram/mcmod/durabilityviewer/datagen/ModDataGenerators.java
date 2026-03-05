package de.guntram.mcmod.durabilityviewer.datagen;

import de.guntram.mcmod.durabilityviewer.DurabilityViewer;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = DurabilityViewer.MODID)
public class ModDataGenerators {
    @SubscribeEvent
    public static void reg(GatherDataEvent.Client event) {



        event.createProvider((packOutput) -> new EnLangProvider(packOutput, DurabilityViewer.MODID, "en_us"));
        event.createProvider(packOutput -> new ZhLangProvider(packOutput, DurabilityViewer.MODID, "zh_cn"));

    }
}
