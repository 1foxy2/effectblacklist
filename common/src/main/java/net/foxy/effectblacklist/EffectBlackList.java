package net.foxy.effectblacklist;

import net.foxy.effectblacklist.config.EffectBlackListConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class EffectBlackList {
    public static final String MOD_ID = "effectblacklist";

    public static final Pair<EffectBlackListConfig, ForgeConfigSpec> CONFIG = new ForgeConfigSpec.Builder()
            .configure(EffectBlackListConfig::new);

    public static void onClientSetup(Path path) {
        EffectBlackListConfig config = CONFIG.getKey();
        if (config.PRINT_EFFECT_ID.get()) {
            try {
                PrintWriter writer = new PrintWriter(path.resolve("effectblacklist-effects.txt").toFile(), StandardCharsets.UTF_8);
                for (ResourceLocation effect : BuiltInRegistries.MOB_EFFECT.keySet()) {
                    writer.write(effect.toString() + "\n");
                }
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
