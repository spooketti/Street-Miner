package spooketti.streetminer;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import spooketti.streetminer.input.InputReader;

public class StreetminerClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		InputReader inputReader = new InputReader();
		inputReader.register();

	}
}