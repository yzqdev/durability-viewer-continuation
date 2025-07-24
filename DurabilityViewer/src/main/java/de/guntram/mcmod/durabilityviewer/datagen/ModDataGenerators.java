package de.guntram.mcmod.durabilityviewer.datagen;

import de.guntram.mcmod.durabilityviewer.DurabilityViewer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = DurabilityViewer.MODID )
public class ModDataGenerators {
    @SubscribeEvent
    public static void reg(GatherDataEvent event){
        var generator=event.getGenerator();
        var packOutput=generator.getPackOutput();
        var lookupProvider=event.getLookupProvider();
        var existingFileHelper=event.getExistingFileHelper();

        generator.addProvider(event.includeServer(),new EnLangProvider(packOutput, DurabilityViewer.MODID,"en_us"));
        generator.addProvider(event.includeServer(),new ZhLangProvider(packOutput,DurabilityViewer.MODID,"zh_cn"));
    }
}
