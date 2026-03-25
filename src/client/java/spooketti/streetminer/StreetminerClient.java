package spooketti.streetminer;

import net.fabricmc.api.ClientModInitializer;
import spooketti.streetminer.input.InputReader;

public class StreetminerClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		InputReader inputReader = new InputReader();
		inputReader.register();
	}
}