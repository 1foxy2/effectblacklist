package net.foxy.effectblacklister;

import net.foxy.effectblacklister.config.EffectBlackListerConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class EffectBlackLister {
    public static final String MOD_ID = "effectblacklister";

    public static final Pair<EffectBlackListerConfig, ForgeConfigSpec> CONFIG = new ForgeConfigSpec.Builder()
            .configure(EffectBlackListerConfig::new);

    public static void onClientSetup(Path path) {
        EffectBlackListerConfig config = CONFIG.getKey();
        if (config.PRINT_EFFECT_ID.get()) {
            try {
                PrintWriter writer = new PrintWriter(path.resolve("effectblacklister-effects.txt").toFile(), StandardCharsets.UTF_8);
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
