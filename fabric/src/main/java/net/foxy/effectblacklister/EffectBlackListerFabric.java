package net.foxy.effectblacklister;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

public class EffectBlackListerFabric implements ModInitializer, ModMenuApi {

    @Override
    public void onInitialize() {
        NeoForgeConfigRegistry.INSTANCE.register(EffectBlackLister.MOD_ID,
                ModConfig.Type.COMMON, EffectBlackLister.CONFIG.getValue());
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new ConfigurationScreen(EffectBlackLister.MOD_ID, parent);
    }
}
