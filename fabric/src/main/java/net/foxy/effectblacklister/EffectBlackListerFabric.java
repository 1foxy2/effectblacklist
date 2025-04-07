package net.foxy.effectblacklister;

import com.terraformersmc.modmenu.api.ModMenuApi;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import fuzs.forgeconfigapiport.api.config.v2.ModConfigEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.foxy.effectblacklister.config.EffectBlackListerConfig;
import net.minecraftforge.fml.config.ModConfig;

public class EffectBlackListerFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        EffectBlackListerConfig config = EffectBlackLister.CONFIG.getKey();
        ForgeConfigRegistry.INSTANCE.register(EffectBlackLister.MOD_ID,
                ModConfig.Type.COMMON, EffectBlackLister.CONFIG.getValue());
        ClientLifecycleEvents.CLIENT_STARTED.register(s ->
                EffectBlackLister.onClientSetup(FabricLoader.getInstance().getConfigDir()));
    }
}
