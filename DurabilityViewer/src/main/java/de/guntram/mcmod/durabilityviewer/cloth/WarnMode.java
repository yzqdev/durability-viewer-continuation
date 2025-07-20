package de.guntram.mcmod.durabilityviewer.cloth;

 

public enum WarnMode {
    NONE(  "durabilityviewer.config.warnmode.none"),
    SOUND(  "durabilityviewer.config.warnmode.sound"),
    VISUAL( "durabilityviewer.config.warnmode.visual"),
    BOTH(  "durabilityviewer.config.warnmode.both");


    private final String translationKey;
    WarnMode(String key) { this.translationKey = key; }
    public String getTranslationKey() { return translationKey; }
}