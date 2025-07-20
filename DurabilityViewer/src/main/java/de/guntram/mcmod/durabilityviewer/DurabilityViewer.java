package de.guntram.mcmod.durabilityviewer;


import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_H;

import com.mojang.blaze3d.platform.InputConstants;

@Mod(DurabilityViewer.MODID)
public class DurabilityViewer {
    public static final String MODID = "durabilityviewer";
    public static final String MOD_NAME = "Durability Viewer";

    public static ClothModConfig getConfig;
    public static DurabilityViewer instance;


    public static final Logger LOGGER = LogManager.getLogger("DurabilityViewer");


    public DurabilityViewer(IEventBus modEventBus, ModContainer modContainer) {


        if (FMLEnvironment.dist == Dist.CLIENT) {
            GuiConfig.getConfig();
        }
        AutoConfig.register(ClothModConfig.class, Toml4jConfigSerializer::new);
        getConfig = AutoConfig.getConfigHolder(ClothModConfig.class).getConfig();


    }



}
