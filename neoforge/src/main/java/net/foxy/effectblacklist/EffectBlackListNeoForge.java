package net.foxy.effectblacklist;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(EffectBlackList.MOD_ID)
public class EffectBlackListNeoForge {
    public EffectBlackListNeoForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EffectBlackList.CONFIG.getValue());
        eventBus.addListener(this::onClientSetup);
    }

    public void onClientSetup(FMLCommonSetupEvent event) {
        EffectBlackList.onClientSetup(FMLPaths.CONFIGDIR.get());
    }
}
