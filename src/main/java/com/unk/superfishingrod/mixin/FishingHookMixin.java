package com.unk.superfishingrod.mixin;

import com.unk.superfishingrod.item.ModItems;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingHook.class)
public class FishingHookMixin {
    @Inject(at = @At("HEAD"), method = "shouldStopFishing", cancellable = true)
    private void onShouldStopFishing(Player owner, CallbackInfoReturnable<Boolean> cir) {
        if (owner == null) return;
        ItemStack mainHand = owner.getMainHandItem();
        ItemStack offHand = owner.getOffhandItem();

        boolean hasRod = mainHand.is(ModItems.SUPER_FISHING_ROD) ||
                offHand.is(ModItems.SUPER_FISHING_ROD);

        if (hasRod) {
            FishingHook hook = (FishingHook) (Object) this;
            if (hook.distanceToSqr(owner) <= 1024.0F) {
                cir.setReturnValue(false);
            }
        }
    }
}