package de.guntram.mcmod.durabilityviewer;

import me.shedaniel.autoconfig.AutoConfig;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public class GuiConfig {
    public static void getConfig(){
        ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> (client, parent) -> AutoConfig.getConfigScreen(ClothModConfig.class, parent).get()
        );
    }
}
