package net.foxy.effectblacklister;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(EffectBlackLister.MOD_ID)
public class EffectBlackListerNeoForge {
    public EffectBlackListerNeoForge(ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, EffectBlackLister.CONFIG.getValue());
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
