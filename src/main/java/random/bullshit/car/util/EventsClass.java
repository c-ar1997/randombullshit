package random.bullshit.car.util;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
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
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.event.BlockPositionSource;
import org.jetbrains.annotations.Nullable;
import random.bullshit.car.RandomBullshit;

public class EventsClass {
    // all the event stuff is here

    public static void selectSingleEvent(int rand, ServerPlayerEntity plr){
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
            case 2: jeb(serverPlayerEntity);
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
        MinecraftClient client = MinecraftClient.getInstance();
        assert client.player != null;
        if (client.player.getName().getString().equals(plr.getName().getString())){
            client.options.getMaxFps().setValue(10);
            client.options.write();
        }
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
            ServerWorld servWorld = (ServerWorld) plr.getWorld();
            WardenEntity warden = EntityType.WARDEN.spawn(plr.getServerWorld(),plr.getBlockPos(),SpawnReason.EVENT);
            assert warden != null;
            warden.setCustomName(Text.literal("Cupcake"));
        }
    }

    public static void boogieWoogie(ServerPlayerEntity plr){
        ServerWorld serverWorld = plr.getServerWorld();
        Random rand = serverWorld.getRandom();
        ServerPlayerEntity swapPlr = serverWorld.getPlayers().get(rand.nextBetween(0,serverWorld.getPlayers().size() - 1));
        if (!swapPlr.getName().contains(plr.getName())){
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
}
