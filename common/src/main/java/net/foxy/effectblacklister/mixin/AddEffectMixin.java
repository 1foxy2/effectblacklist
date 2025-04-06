package net.foxy.effectblacklister.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.mojang.logging.LogUtils;
import net.foxy.effectblacklister.EffectBlackLister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class AddEffectMixin {
    @Inject(
            method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;",
                    shift = At.Shift.BEFORE,
                    remap = false
            ),
            cancellable = true
    )
    private void effectblacklister$modifyEffect(MobEffectInstance effectInstance, Entity entity,
                                                CallbackInfoReturnable<Boolean> cir) {
        for (String effectId : EffectBlackLister.CONFIG.getLeft().EFFECTS.get()) {
            String[] effectIds = effectId.split("=");
            if (effectIds[0].equals(effectInstance.getEffect().getRegisteredName())) {
                if (effectIds.length == 1) {
                    cir.setReturnValue(false);
                } else {
                    BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse(effectIds[1]))
                            .ifPresent(effect -> effectInstance.effect = effect);
                }
                return;
            }
        }
    }
}
