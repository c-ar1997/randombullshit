package random.bullshit.car.sounds;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import random.bullshit.car.RandomBullshit;

public class ModSounds {
    public static void registerSoundevents() {RandomBullshit.LOGGER.info("registering soundevents");}
    public static final SoundEvent BIRD = registerSound("bird");

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of(RandomBullshit.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }
}
