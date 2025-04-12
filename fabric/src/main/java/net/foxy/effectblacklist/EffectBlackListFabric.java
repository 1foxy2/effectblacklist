package net.foxy.effectblacklist;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.foxy.effectblacklist.config.EffectBlackListConfig;
import net.minecraftforge.fml.config.ModConfig;

public class EffectBlackListFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        EffectBlackListConfig config = EffectBlackList.CONFIG.getKey();
        ForgeConfigRegistry.INSTANCE.register(EffectBlackList.MOD_ID,
                ModConfig.Type.COMMON, EffectBlackList.CONFIG.getValue());
        ClientLifecycleEvents.CLIENT_STARTED.register(s ->
                EffectBlackList.onClientSetup(FabricLoader.getInstance().getConfigDir()));
    }
}
