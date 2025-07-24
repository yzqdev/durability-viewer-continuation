package de.guntram.mcmod.durabilityviewer;

import de.guntram.mcmod.durabilityviewer.cloth.Corner;
import de.guntram.mcmod.durabilityviewer.cloth.WarnMode;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@EventBusSubscriber(modid = DurabilityViewer.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // 配置项定义
    private static final ModConfigSpec.BooleanValue SHOW_INVENTORY_ITEM_DURABILITY = BUILDER
            .comment("show inventory item durability")
            .define("showInventoryItemDurability", true);

    private static final ModConfigSpec.BooleanValue ARMOR_AROUND_HOTBAR = BUILDER
            .comment("Armor Around Hotbar")
            .define("armorAroundHotbar", false);

    private static final ModConfigSpec.BooleanValue EFFECT_DURATION = BUILDER
            .comment("show effect duration")
            .define("effectDuration", true);

    private static final ModConfigSpec.EnumValue<Corner> HUD_POSITION = BUILDER
            .comment("HUD position")
            .defineEnum("hudPosition", Corner.BOTTOM_RIGHT);

    private static final ModConfigSpec.IntValue HIDE_DAMAGE_OVER_PERCENT = BUILDER
            .comment("hide hud damage over percent")
            .defineInRange("hideDamageOverPercent", 100, 0, 100);

    private static final ModConfigSpec.IntValue SOUND_BELOW_DURABILITY = BUILDER
            .comment("play sound when durability is lower than this")
            .defineInRange("soundBelowDurability", 100, 0, 1500);    private static final ModConfigSpec.IntValue SOUND_BELOW_PERCENT = BUILDER
            .comment("play sound when percent is lower than this")
            .defineInRange("soundBelowPercent", 100, 0, 100);

    private static final ModConfigSpec.BooleanValue SHOW_PERCENT_RATHER_THAN_DURABILITY = BUILDER
            .comment("show percent rather than durability")
            .define("showPercentRatherThanDurability", false);

    private static final ModConfigSpec.IntValue PERCENT_TO_SHOW_DAMAGE_RATHER_THAN_DURABILITY = BUILDER
            .comment("percent to show damage rather than durability")
            .defineInRange("percentToShowDamageRatherThanDurability", 80, 0, 100);

    private static final ModConfigSpec.BooleanValue SHOW_FREE_INVENTORY_SLOTS = BUILDER
            .comment("show free inventory slots")
            .define("showFreeInventorySlots", true);

    private static final ModConfigSpec.EnumValue<WarnMode> WARNING_MODE = BUILDER
            .comment("warning mode")
            .defineEnum("warningMode", WarnMode.BOTH);
    private static final ModConfigSpec.BooleanValue SHOW_ALL_TRINKETS=BUILDER.comment("show  trinkets").define("showAllTrinkets", true);

    static final ModConfigSpec SPEC = BUILDER.build();


    public static boolean showInventoryItemDurability;
    public static boolean armorAroundHotbar;
    public static boolean effectDuration;
    public static Corner hudPosition;
    public static int hideDamageOverPercent;
    public static int soundBelowDurability;
    public static int soundBelowPercent ;
    public static boolean showPercentRatherThanDurability;
    public static int percentToShowDamageRatherThanDurability;
    public static boolean showFreeInventorySlots;
    public static WarnMode warningMode;
public static boolean showAllTrinkets;
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {

        showInventoryItemDurability = SHOW_INVENTORY_ITEM_DURABILITY.get();
        armorAroundHotbar = ARMOR_AROUND_HOTBAR.get();
        effectDuration = EFFECT_DURATION.get();
        hudPosition = HUD_POSITION.get();
        hideDamageOverPercent = HIDE_DAMAGE_OVER_PERCENT.get();
        soundBelowDurability = SOUND_BELOW_DURABILITY.get();
        soundBelowPercent = SOUND_BELOW_PERCENT.get();
        showPercentRatherThanDurability = SHOW_PERCENT_RATHER_THAN_DURABILITY.get();
        percentToShowDamageRatherThanDurability = PERCENT_TO_SHOW_DAMAGE_RATHER_THAN_DURABILITY.get();
        showFreeInventorySlots = SHOW_FREE_INVENTORY_SLOTS.get();
        warningMode = WARNING_MODE.get();
        showAllTrinkets= SHOW_ALL_TRINKETS.get();
    }


}