package net.foxy.effectblacklister;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(EffectBlackLister.MOD_ID)
public class EffectBlackListerNeoForge {
    public EffectBlackListerNeoForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EffectBlackLister.CONFIG.getValue());
        eventBus.addListener(this::onClientSetup);
    }

    public void onClientSetup(FMLCommonSetupEvent event) {
        EffectBlackLister.onClientSetup(FMLPaths.CONFIGDIR.get());
    }
}
