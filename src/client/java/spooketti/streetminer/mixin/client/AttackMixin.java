package spooketti.streetminer.mixin.client;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.player.LocalPlayer;
import spooketti.streetminer.input.InputReader;

@Mixin(LocalPlayer.class)
public class AttackMixin {
//	@Inject(at = @At("HEAD"), method = "run")
//	private void init(CallbackInfo info) {
//		// This code is injected into the start of Minecraft.run()V
//	}

	@Inject(at = @At("HEAD"), method="swing")
	private void attack()
	{
		System.out.println(InputReader.buffer);
	}
}