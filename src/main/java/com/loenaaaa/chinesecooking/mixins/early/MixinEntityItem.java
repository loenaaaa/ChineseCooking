package com.loenaaaa.chinesecooking.mixins.early;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.loenaaaa.chinesecooking.ModItems;
import com.loenaaaa.chinesecooking.utils.ItemRegistryUtils;

@Mixin(value = EntityItem.class)
public abstract class MixinEntityItem extends Entity {

    public MixinEntityItem(World worldIn) {
        super(worldIn);
    }

    @Inject(method = "attackEntityFrom", at = @At("HEAD"))
    private void chineseCooking$convertIntoWok(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source == DamageSource.anvil) {
            EntityItem theEntityItem = (EntityItem) (Object) this;
            if (theEntityItem.getEntityItem()
                .getItem() != ItemRegistryUtils.getHarvestcraftItem("potItem")) return;
            theEntityItem.getDataWatcher()
                .getWatchableObjectItemStack(10)
                .func_150996_a(ModItems.WOK.getItem());
        }
    }
}
