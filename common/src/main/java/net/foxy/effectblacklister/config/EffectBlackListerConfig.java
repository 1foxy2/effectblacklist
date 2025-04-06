package net.foxy.effectblacklister.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class EffectBlackListerConfig {
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> EFFECTS;

    public EffectBlackListerConfig(ForgeConfigSpec.Builder builder) {
        EFFECTS = builder.comment("Add effect id to remove it. to replace add following \"effect_id=effect_id\"")
                .defineList("effects", new ArrayList<>(), object -> object instanceof String);
    }
}
