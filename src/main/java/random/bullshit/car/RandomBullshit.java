package random.bullshit.car;

import com.ibm.icu.impl.Pair;
import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import random.bullshit.car.command.StartEvent;
import random.bullshit.car.util.EventsClass;

import static net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.START_SERVER_TICK;

public class RandomBullshit implements ModInitializer {
	public static final String MOD_ID = "randombullshit";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	static @Nullable ServerPlayerEntity serverPlayerEntity = null;
	static @Nullable Integer timer = null;
	static @Nullable Boolean isGlobal = false;

	static @Nullable ServerPlayerEntity tntPlrEntity = null;
	static @Nullable Boolean tntActive = false;
	int tntTimer = 80;

	public static void setTimer(@Nullable ServerPlayerEntity serverPlayerEntity, @Nullable Integer timer, @Nullable Boolean isGlobal) {
		RandomBullshit.serverPlayerEntity = serverPlayerEntity;
		RandomBullshit.timer = timer;
		RandomBullshit.isGlobal = isGlobal;
	}

	public static void setTnt(@Nullable ServerPlayerEntity tntPlrEntity, @Nullable Boolean tntActive){
		RandomBullshit.tntPlrEntity = tntPlrEntity;
		RandomBullshit.tntActive = tntActive;
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		StartEvent.StartEvent();
		START_SERVER_TICK.register(server -> {
			if (timer == null) return;
			timer = timer - 1;
			if (timer == 0) {
				if (!isGlobal){
					EventsClass.selectSingleEvent(serverPlayerEntity.getWorld().getRandom().nextBetween(0,18),serverPlayerEntity);
				} else {
					EventsClass.selectAllEvent(serverPlayerEntity.getWorld().getRandom().nextBetween(0,2),serverPlayerEntity);
				}
				timer = null;
				serverPlayerEntity = null;
				isGlobal = null;
			}

		});
		START_SERVER_TICK.register(minecraftServer -> {
			if (tntActive == false) return;
				tntPlrEntity.getServerWorld().getPlayers().forEach(player -> {
					if (!player.getWorld().isClient()){
						Random rand = player.getWorld().getRandom();
						if (tntTimer >= 0){
							tntTimer--;
							player.getWorld().spawnEntity(EntityType.TNT_MINECART.spawn(player.getServerWorld(),
									new BlockPos((int) (player.getX() + rand.nextBetween(-25,25)),
											(int) (player.getY() + 50),
											(int) (player.getZ() + rand.nextBetween(-25,25))) , SpawnReason.EVENT));
						} else {
							tntPlrEntity = null;
							tntActive = false;
						}
					}
				});
		});
		LOGGER.info("RANDOM BULLSHIT GO");
	}
}