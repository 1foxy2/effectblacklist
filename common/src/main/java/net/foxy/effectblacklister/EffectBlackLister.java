package net.foxy.effectblacklister;

import net.foxy.effectblacklister.config.EffectBlackListerConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class EffectBlackLister {
    public static final String MOD_ID = "effectblacklister";

    public static final Pair<EffectBlackListerConfig, ModConfigSpec> CONFIG = new ModConfigSpec.Builder()
            .configure(EffectBlackListerConfig::new);
}
