package net.foxy.effectblacklist.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.foxy.effectblacklist.EffectBlackList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class ForgeItemStackMixin implements IForgeItemStack {
    @WrapOperation(
            method = "getFoodProperties",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/Item;getFoodProperties(Lnet/minecraft/world/item/ItemStack;" +
                            "Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/food/FoodProperties;"
            ),
            remap = false
    )
    FoodProperties replaceFoodProperties(Item instance, ItemStack itemStack, LivingEntity livingEntity,
                                         Operation<FoodProperties> original) {
        FoodProperties properties = original.call(instance, itemStack, livingEntity);

        if (properties == null || properties.getEffects().isEmpty()) {
            return properties;
        }

        if (EffectBlackList.CONFIG.getKey().FOODS.get().contains(BuiltInRegistries.ITEM.getKey(instance).toString())) {
            return new FoodProperties(properties.getNutrition(), properties.getSaturationModifier(),
                    properties.isMeat(), properties.canAlwaysEat(), properties.isFastFood(), List.of());
        }
        return properties;
    }
}
