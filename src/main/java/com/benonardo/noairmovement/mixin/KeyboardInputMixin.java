package com.benonardo.noairmovement.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin {

    @Inject(method = "getMovementMultiplier", at = @At(value = "HEAD"), cancellable = true)
    private static void dontMoveWhenInAir(CallbackInfoReturnable<Float> cir) {
        ClientPlayerEntity player;
        if (!((player = MinecraftClient.getInstance().player) == null || player.isCreative() || player.isSpectator() || player.isOnGround())) {
            cir.setReturnValue(0.0f);
        }
    }

}
