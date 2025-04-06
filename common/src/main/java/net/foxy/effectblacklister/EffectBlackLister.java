package net.foxy.effectblacklister;

import net.foxy.effectblacklister.config.EffectBlackListerConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class EffectBlackLister {
    public static final String MOD_ID = "effectblacklister";

    public static final Pair<EffectBlackListerConfig, ForgeConfigSpec> CONFIG = new ForgeConfigSpec.Builder()
            .configure(EffectBlackListerConfig::new);
}
