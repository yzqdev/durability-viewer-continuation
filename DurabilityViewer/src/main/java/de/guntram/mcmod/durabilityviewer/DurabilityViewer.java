package de.guntram.mcmod.durabilityviewer;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(value = DurabilityViewer.MODID,dist = Dist.CLIENT)
public class DurabilityViewer {
    public static final String MODID = "durabilityviewer";
//    public static final String MOD_NAME = "Durability Viewer";
    public static final Logger LOGGER = LogManager.getLogger(DurabilityViewer.MODID);

    public DurabilityViewer(IEventBus modEventBus, ModContainer modContainer) {

        modContainer.registerConfig(ModConfig.Type.COMMON,Config.SPEC);

        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (container, modListScreen) -> new ConfigurationScreen(container, modListScreen, ModConfigScreen::new));

    }



}
