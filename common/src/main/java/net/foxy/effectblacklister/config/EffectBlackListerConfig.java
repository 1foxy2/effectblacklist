package net.foxy.effectblacklister.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class EffectBlackListerConfig {
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> EFFECTS;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> FOODS;
    public final ForgeConfigSpec.BooleanValue PRINT_EFFECT_ID;

    public EffectBlackListerConfig(ForgeConfigSpec.Builder builder) {
        EFFECTS = builder.comment("Add effect id to remove it. to replace add following \"effect_id=effect_id\"")
                .defineList("effects", new ArrayList<>(), object -> object instanceof String);
        FOODS = builder.comment("Add food item id to remove effects from it.")
                .defineList("foods", new ArrayList<>(), object -> object instanceof String);
        PRINT_EFFECT_ID = builder.comment("Prints all effects id into file")
                .define("print_effect_id", false);
    }
}
