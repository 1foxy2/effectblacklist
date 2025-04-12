package net.foxy.effectblacklist.mixin;

import net.foxy.effectblacklist.EffectBlackList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(
            method = "getTooltipLines",
            at = @At(value = "RETURN")
    )
    private void effectblacklist$removeEffectTooltip(Player player, TooltipFlag isAdvanced,
                                            CallbackInfoReturnable<List<Component>> cir) {
        if (EffectBlackList.CONFIG.getKey().FOODS.get()
                .contains(BuiltInRegistries.ITEM.getKey(((ItemStack) (Object) this).getItem()).toString())) {
            cir.getReturnValue().removeIf(component -> {
                if (component.getContents() instanceof TranslatableContents contents) {
                    String key = contents.getKey();
                    return key.equals("potion.withAmplifier") || key.equals("potion.withDuration");
                }
                return false;
            });
        }
    }
}
