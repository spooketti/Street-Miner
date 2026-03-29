package spooketti.streetminer.audio;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import spooketti.streetminer.Streetminer;

public class AudioRegister {
    private AudioRegister() {
        // private empty constructor to avoid accidental instantiation
    }

    public class RYU
    {
        public static SoundEvent SHORYUKEN_LIGHT;
        public static SoundEvent SHORYUKEN_HEAVY;
    }

    public class TERRY
    {

    }

    // actual registration of all the custom SoundEvents
    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.fromNamespaceAndPath(Streetminer.MOD_ID, id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    // This static method starts class initialization, which then initializes
    // the static class variables (e.g. ITEM_METAL_WHISTLE).
    public static void initialize() {
        RYU.SHORYUKEN_LIGHT = registerSound("shoryuken_light");
        RYU.SHORYUKEN_HEAVY = registerSound("shoryuken_heavy");
        Streetminer.LOGGER.info("Registering " + Streetminer.MOD_ID + " Sounds");
        // Technically this method can stay empty, but some developers like to notify
        // the console, that certain parts of the mod have been successfully initialized
    }
}