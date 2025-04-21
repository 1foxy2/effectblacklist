package net.foxy.effectblacklist.mixin;

import net.foxy.effectblacklist.EffectBlackList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class ForgeItemStackMixin implements IForgeItemStack {
    @Shadow public abstract Item getItem();

    @Override
    public FoodProperties getFoodProperties(LivingEntity entity) {
        FoodProperties properties = getItem().getFoodProperties((ItemStack) (Object) this, entity);
        if (properties == null || properties.getEffects().isEmpty()) {
            return properties;
        }

        if (EffectBlackList.CONFIG.getKey().FOODS.get().contains(BuiltInRegistries.ITEM.getKey(getItem()).toString())) {
            return new FoodProperties(properties.getNutrition(), properties.getSaturationModifier(),
                    properties.isMeat(), properties.canAlwaysEat(), properties.isFastFood(), List.of());
        }
        return properties;
    }
}
