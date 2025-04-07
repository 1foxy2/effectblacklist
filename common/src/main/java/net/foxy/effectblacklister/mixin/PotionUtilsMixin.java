package net.foxy.effectblacklister.mixin;

import net.foxy.effectblacklister.EffectBlackLister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PotionUtils.class)
public class PotionUtilsMixin {
    @Inject(
            method = "addPotionTooltip(Lnet/minecraft/world/item/ItemStack;Ljava/util/List;F)V",
            cancellable = true,
            at = @At(value = "HEAD")
    )
    private static void removeEffectTooltip(ItemStack stack, List<Component> tooltips,
                                            float durationFactor, CallbackInfo ci) {
        if (EffectBlackLister.CONFIG.getKey().FOODS.get()
                .contains(BuiltInRegistries.ITEM.getKey(stack.getItem()).toString())) {
            ci.cancel();
        }
    }
}
