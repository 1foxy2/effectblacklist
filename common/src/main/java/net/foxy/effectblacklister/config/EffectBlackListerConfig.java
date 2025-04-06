package net.foxy.effectblacklister.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class EffectBlackListerConfig {
    public final ModConfigSpec.ConfigValue<List<? extends String>> EFFECTS;

    public EffectBlackListerConfig(ModConfigSpec.Builder builder) {
        EFFECTS = builder.comment("Add effect id to remove it. to replace add following \"effect_id=effect_id\"")
                .defineList("effects", ArrayList::new, String::new, object -> object instanceof String);
    }
}
