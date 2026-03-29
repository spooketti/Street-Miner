package spooketti.streetminer;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.KeyMapping;
import spooketti.streetminer.audio.AudioRegister;
import spooketti.streetminer.input.InputReader;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;

public class StreetminerClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		InputReader inputReader = new InputReader();
		inputReader.register();
//		KeyMapping idk = KeyMappingHelper.registerKeyMapping(new KeyMapping(""))
	}
}