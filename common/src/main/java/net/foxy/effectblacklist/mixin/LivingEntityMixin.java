package net.foxy.effectblacklist.mixin;

import net.foxy.effectblacklist.EffectBlackList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
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
    private void effectblacklist$modifyEffect(MobEffectInstance effectInstance, Entity entity,
                                                CallbackInfoReturnable<Boolean> cir) {
        for (String effectId : EffectBlackList.CONFIG.getLeft().EFFECTS.get()) {
            String[] effectIds = effectId.split("=");
            if (effectIds[0].equals(BuiltInRegistries.MOB_EFFECT.getKey(effectInstance.getEffect()).toString())) {
                if (effectIds.length == 1) {
                    cir.setReturnValue(false);
                } else {
                    BuiltInRegistries.MOB_EFFECT.getOptional(new ResourceLocation(effectIds[1]))
                            .ifPresent(effect -> effectInstance.effect = effect);
                }
                return;
            }
        }
    }

    @Inject(
            method = "addEatEffect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/Item;isEdible()Z",
                    shift = At.Shift.AFTER,
                    remap = false
            ),
            cancellable = true
    )
    private void effectblacklist$modifyEffect(ItemStack food, Level level,
                                                LivingEntity livingEntity, CallbackInfo ci) {
        if (EffectBlackList.CONFIG.getKey().FOODS.get().contains(BuiltInRegistries.ITEM.getKey(food.getItem()).toString())) {
            ci.cancel();
        }
    }
}
