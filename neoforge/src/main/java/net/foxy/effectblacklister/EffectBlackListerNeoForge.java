package net.foxy.effectblacklister;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(EffectBlackLister.MOD_ID)
public class EffectBlackListerNeoForge {
    public EffectBlackListerNeoForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EffectBlackLister.CONFIG.getValue());
    }
}
