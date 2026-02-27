package random.bullshit.car.util;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.SubtitleS2CPacket;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stat;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.thread.LockHelper;
import net.minecraft.world.event.BlockPositionSource;
import org.jetbrains.annotations.Nullable;
import random.bullshit.car.RandomBullshit;
import random.bullshit.car.sounds.ModSounds;

public class EventsClass {
    // all the event stuff is here

    public static void selectSingleEvent(int rand, ServerPlayerEntity plr){
        plr.networkHandler.sendPacket(new SubtitleS2CPacket(Text.of("")));
        plr.getWorld().playSound(null,plr.getBlockPos(),
                SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), SoundCategory.MASTER,1f,1f);
        switch (rand){
            case 0: summonLightning(plr); break;
            case 1: summonCreeper(plr); break;
            case 2: giveTotem(plr); break;
            case 3: giveCore(plr); break;
            case 4: shortSpeed(plr); break;
            case 5: fuckYou(plr); break;
            case 6: fat(plr); break;
            case 7: foodPoisoning(plr); break;
            case 8: charity(plr); break;
            case 9: boogieWoogie(plr); break;
            case 10: warden(plr); break;
            case 11: dementia(plr); break;
            case 12: superman(plr); break;
            case 13: strength(plr); break;
            case 14: yuuki(plr); break;
            case 15: noodleArms(plr); break;
            case 16: pregnancy(plr); break;
            case 17: giveWither(plr); break;
            case 18: hypercam(plr); break;
            case 19: hellenkeller(plr); break;
            case 20: airhorn(plr); break;
            case 21: redcircle(plr); break;
            case 22: immortality(plr); break;
            case 23: fakeCreeper(plr); break;
            case 24: herobrine(plr); break;
            case 25: fakeHurt(plr); break;
            case 26: alex(plr); break;
            case 27: crash(plr); break;
            case 28: window(plr); break;
            case 29: invert(plr); break;
            case 30: chopped(plr); break;
            default:
                RandomBullshit.LOGGER.info("something went wrong apparently");
        }
    }

    public static void selectAllEvent(int rand, ServerPlayerEntity serverPlayerEntity){
        serverPlayerEntity.getWorld().playSound(null,serverPlayerEntity.getBlockPos(),
                SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), SoundCategory.MASTER,1f,1f);
        switch(rand){
            case 0: tntCartRain(serverPlayerEntity); break;
            case 1: globalBlindness(serverPlayerEntity); break;
            case 2: jeb(serverPlayerEntity); break;
            case 3: globalBoogieWoogie(serverPlayerEntity); break;
            default: RandomBullshit.LOGGER.info("something went wrong apparently");
        }
    }

    public static void summonLightning(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient) {
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Lightning Bolt §r§4||§r")));
            ServerWorld servWorld = (ServerWorld) plr.getWorld();
            plr.getWorld().spawnEntity(EntityType.LIGHTNING_BOLT.spawn(servWorld,plr.getBlockPos(), SpawnReason.EVENT));
        }
    }

    public static void summonCreeper(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient) {
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Creeper Swarm §r§4||§r")));
            ServerWorld servWorld = (ServerWorld) plr.getWorld();
            for (int i = 20; i >= 0; i--){
                plr.getWorld().spawnEntity(EntityType.CREEPER.spawn(servWorld,plr.getBlockPos(), SpawnReason.EVENT));
            }
        }
    }

    public static void fakeCreeper(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient) {
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Creeper Swarm §r§4||§r")));
            plr.getWorld().playSound(null,plr.getBlockPos(),
                    SoundEvents.ENTITY_CREEPER_PRIMED, SoundCategory.MASTER,10f,1f);
        }
    }

    public static void fakeHurt(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient) {
            plr.getWorld().playSound(null,plr.getBlockPos(),
                    SoundEvents.ENTITY_PLAYER_HURT, SoundCategory.MASTER,10f,1f);
        }
    }

    public static void herobrine(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.getWorld().playSound(null,plr.getBlockPos(),
                    SoundEvents.AMBIENT_CAVE.value(), SoundCategory.MASTER,10f,1f);
            plr.sendMessage(Text.literal("§eHerobrine has joined the game"));
            plr.sendMessage(Text.literal("<Herobrine> Your time has come, " + plr.getName().getString() + "."));
        }
    }

    public static void pregnancy(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient) {
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Congratulations! §r§4||§r")));
            for (int i = 5; i >= 0; i--){
                ZombieEntity zombie = EntityType.ZOMBIE.spawn(plr.getServerWorld(),plr.getBlockPos(),SpawnReason.EVENT);
                assert zombie != null;
                zombie.setBaby(true);
            }
        }
    }

    public static void hypercam(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient) {
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Unregistered Hypercam §r§4||§r")));
            }
        ServerPlayNetworking.send(plr,new HypercamPayloadS2C(plr.getBlockPos()));
    }
    public static void hellenkeller(ServerPlayerEntity plr){
        ServerPlayNetworking.send(plr,new HellenKellerPayloadS2C(plr.getBlockPos()));
        plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Hellen Keller §r§4||§r")));
        plr.setStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS,1200,255), plr);
    }

    public static void airhorn(ServerPlayerEntity plr){
        ServerPlayNetworking.send(plr, new AirhornPayloadS2C(plr.getBlockPos()));
        plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Airhorn §r§4||§r")));
        plr.getWorld().playSound(null,plr.getBlockPos(),
                ModSounds.BIRD, SoundCategory.MASTER,1f,1f);
    }

    public static void giveTotem(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Totem of Undying! §r§4||§r")));
            plr.getInventory().setStack(plr.getInventory().getEmptySlot(),Items.TOTEM_OF_UNDYING.getDefaultStack());
        }
    }

    public static void giveWither(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Use it wisely. :3 §r§4||§r")));
            plr.getInventory().setStack(plr.getInventory().getEmptySlot(),Items.WITHER_SPAWN_EGG.getDefaultStack());
        }
    }

    public static void giveCore(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Heavy Core! §r§4||§r")));
            plr.getInventory().setStack(plr.getInventory().getEmptySlot(),Items.HEAVY_CORE.getDefaultStack());
        }
    }

    public static void charity(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Charity! §r§4||§r")));
            plr.getInventory().setStack(plr.getInventory().getEmptySlot(),new ItemStack(Items.BREAD,5));
        }
    }

    public static void fuckYou(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Fuck you. §r§4||§r")));
        }
    }

    public static void shortSpeed(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Heart Attack! §r§4||§r")));
            plr.setStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 60, 255), plr);
        }
    }

    public static void foodPoisoning(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Food Poisoning! §r§4||§r")));
            plr.setStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 1200, 255), plr);
        }
    }

    public static void fat(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Fatass! §r§4||§r")));
            plr.setStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 240, 255), plr);
        }
    }

    public static void warden(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient) {
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Say hi to Cupcake :3 §r§4||§r")));
            WardenEntity warden = EntityType.WARDEN.spawn(plr.getServerWorld(),plr.getBlockPos(),SpawnReason.EVENT);
            assert warden != null;
            warden.setCustomName(Text.literal("Cupcake"));
        }
    }

    public static void boogieWoogie(ServerPlayerEntity plr){
        ServerWorld serverWorld = plr.getServerWorld();
        Random rand = serverWorld.getRandom();
        ServerPlayerEntity swapPlr = serverWorld.getPlayers().get(rand.nextBetween(0,serverWorld.getPlayers().size() - 1));
        if (!swapPlr.getName().contains(plr.getName()) && !swapPlr.isCreative() && !swapPlr.isSpectator()){
            double swpPlrX = swapPlr.getX();
            double swpPlrY = swapPlr.getY();
            double swpPlrZ = swapPlr.getZ();
            double plrX = plr.getX();
            double plrY = plr.getY();
            double plrZ = plr.getZ();
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Swap! §r§4||§r")));
            swapPlr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Swap! §r§4||§r")));
            swapPlr.setPos(plrX,plrY,plrZ);
            plr.setPos(swpPlrX,swpPlrY,swpPlrZ);
            if (rand.nextInt(9) == 1){
                TntEntity tnt = EntityType.TNT.spawn(serverWorld,swapPlr.getBlockPos(),SpawnReason.EVENT);
                assert tnt != null;
                tnt.setFuse(3);
            }
        } else {
            boogieWoogie(plr);
        }
    }

    public static void dementia(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            ItemStack randStack = plr.getInventory().getMainHandStack();
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Dementia. §r§4||§r")));
            randStack.decrement(100);
        }
    }

    public static void superman(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.giveItemStack(Items.WATER_BUCKET.getDefaultStack());
            plr.setStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 5, 255), plr);
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Superman! §r§4||§r")));
            plr.networkHandler.sendPacket(new SubtitleS2CPacket(Text.of("§4||§r§d you have a bucket btw :3 §r§4||§r")));
        }
    }

    public static void strength(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Strength! §r§4||§r")));
            plr.setStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200, 1), plr);
        }
    }

    public static void yuuki(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Yuuki! §r§4||§r")));
            plr.getInventory().setStack(plr.getInventory().getEmptySlot(),Items.DIAMOND_BLOCK.getDefaultStack());
        }
    }

    public static void noodleArms(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Noodle Arms! §r§4||§r")));
            plr.setStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 1200, 1), plr);
        }
    }

    public static void redcircle(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Big Red Circle §r§4||§r")));
            plr.setStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1200, 1), plr);
        }
    }

    public static void immortality(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Immortality §r§4||§r")));
            plr.setStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 255), plr);
        }
    }

    public static void alex(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Alex! §r§4||§r")));
            plr.getInventory().setStack(plr.getInventory().getEmptySlot(),new ItemStack(Items.COAL,1));
        }
    }

    public static void crash(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Think fast, chuckle nuts! §r§4||§r")));
            ServerPlayNetworking.send(plr,new CrashPayloadS2C(plr.getBlockPos()));
        }
    }

    public static void window(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d 144p §r§4||§r")));
            ServerPlayNetworking.send(plr, new WindowPayloadS2C(plr.getBlockPos()));
        }
    }

    public static void invert(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Drunk! §r§4||§r")));
            plr.setStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,600,255), plr);
            ServerPlayNetworking.send(plr, new DrunkPayloadS2C(plr.getBlockPos()));
        }
    }
    public static void chopped(ServerPlayerEntity plr){
        if (!plr.getWorld().isClient()){
            plr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Chopped! §r§4||§r")));
            ServerPlayNetworking.send(plr, new ChoppedPayloadS2C(plr.getBlockPos()));
        }
    }

    // global shi below

    public static void tntCartRain(ServerPlayerEntity serverPlayerEntity){
        serverPlayerEntity.getServerWorld().getPlayers().forEach(serverPlayerEntity1 -> {
            serverPlayerEntity1.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Meteor Shower §r§4||§r")));
        });
        RandomBullshit.setTnt(serverPlayerEntity,true);
    }

    public static void globalBlindness(ServerPlayerEntity serverPlayer){
        serverPlayer.getServerWorld().getPlayers().forEach(serverPlayerEntity -> {
            serverPlayerEntity.setStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS,1200),serverPlayerEntity);
            serverPlayerEntity.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Global Darkness! §r§4||§r")));
        });
    }

    public static void jeb(ServerPlayerEntity serverPlayerEntity){
        serverPlayerEntity.getServerWorld().getPlayers().forEach(serverPlayerEntity1 -> {
            serverPlayerEntity1.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d jeb_ §r§4||§r")));
            SheepEntity sheep = EntityType.SHEEP.spawn(serverPlayerEntity1.getServerWorld(),serverPlayerEntity1.getBlockPos(),SpawnReason.EVENT);
            sheep.setCustomName(Text.literal("jeb_"));
        });
    }

    public static void globalBoogieWoogie(ServerPlayerEntity plr){
        plr.getServerWorld().getPlayers().forEach(serverPlayerEntity -> {
            ServerWorld serverWorld = serverPlayerEntity.getServerWorld();
            Random rand = serverWorld.getRandom();
            ServerPlayerEntity swapPlr = serverWorld.getPlayers().get(rand.nextBetween(0,serverWorld.getPlayers().size() - 1));
            if (!swapPlr.getName().contains(serverPlayerEntity.getName()) && !swapPlr.isCreative() && !swapPlr.isSpectator()){
                double swpPlrX = swapPlr.getX();
                double swpPlrY = swapPlr.getY();
                double swpPlrZ = swapPlr.getZ();
                double plrX = serverPlayerEntity.getX();
                double plrY = serverPlayerEntity.getY();
                double plrZ = serverPlayerEntity.getZ();
                serverPlayerEntity.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Disperse! §r§4||§r")));
                swapPlr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§d Disperse! §r§4||§r")));
                swapPlr.setPos(plrX,plrY,plrZ);
                serverPlayerEntity.setPos(swpPlrX,swpPlrY,swpPlrZ);
            }
        });
    }
}
