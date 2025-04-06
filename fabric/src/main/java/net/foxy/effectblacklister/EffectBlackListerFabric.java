package net.foxy.effectblacklister;

import com.terraformersmc.modmenu.api.ModMenuApi;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraftforge.fml.config.ModConfig;

public class EffectBlackListerFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ForgeConfigRegistry.INSTANCE.register(EffectBlackLister.MOD_ID,
                ModConfig.Type.COMMON, EffectBlackLister.CONFIG.getValue());
    }
}
