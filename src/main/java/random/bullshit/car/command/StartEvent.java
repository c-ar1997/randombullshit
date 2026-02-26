package random.bullshit.car.command;

import com.ibm.icu.impl.Pair;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;
import random.bullshit.car.RandomBullshit;

public class StartEvent {
    public static void StartEvent() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            commandDispatcher.register(CommandManager.literal("startevent").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                    .executes(commandContext -> {
                if (!commandContext.getSource().getPlayer().getWorld().isClient()) {
                    boolean creative = true;
                    int timer = 15;
                    while(creative){
                        creative = false;
                        timer--;
                        Random random = commandContext.getSource().getPlayer().getWorld().getRandom();
                        ServerPlayerEntity rPlr = (ServerPlayerEntity) commandContext.getSource().getPlayer().getWorld().getPlayers().get(random.nextBetween(0,commandContext.getSource().getPlayer().getWorld().getPlayers().size() - 1));
                        if (!rPlr.isCreative() && !rPlr.isSpectator()) {
                            commandContext.getSource().sendMessage(Text.literal("Selected " + rPlr.getName().getString() + " for an event." ));
                            rPlr.getWorld().playSound(null,rPlr.getBlockPos(),
                                    SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.MASTER,1f,1f);
                            rPlr.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§6 You have been selected for... §r§4||§r")));
                            RandomBullshit.setTimer(rPlr,60, false);
                        } else {
                            creative = true;
                            if (timer<=0){
                                break;
                            }
                        }
                    }
                } else {
                    commandContext.getSource().sendError(Text.literal("WHAT IS WROOONG WHAT"));
                }
                return 0;
            })
                    .then(CommandManager.argument("player", StringArgumentType.string())
                    .executes(commandContext -> {
                        if (!commandContext.getSource().getPlayer().getWorld().isClient()) {
                            String playerName = StringArgumentType.getString(commandContext, "player");
                            ServerPlayerEntity targetPlayer = commandContext.getSource().getServer().getPlayerManager().getPlayer(playerName);
                            ServerPlayerEntity sourcePlayer = commandContext.getSource().getPlayer();
                            if (targetPlayer != null && sourcePlayer != null){
                                commandContext.getSource().sendMessage(Text.literal("Selected " + targetPlayer.getName().getString() + " for an event." ));
                                targetPlayer.getWorld().playSound(null,targetPlayer.getBlockPos(),
                                        SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.MASTER,1f,1f);
                                targetPlayer.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§6 You have been selected for... §r§4||§r")));
                                RandomBullshit.setTimer(targetPlayer,60, false);
                            } else {
                                commandContext.getSource().sendError(Text.literal("Player not found RETAAARD"));
                            }
                        }
                        return 0;
                    })));
        });
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            commandDispatcher.register(CommandManager.literal("globalevent").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                    .executes(commandContext -> {
                if (!commandContext.getSource().getPlayer().getWorld().isClient()) {
                    commandContext.getSource().getPlayer().getServerWorld().getPlayers().forEach(serverPlayerEntity -> {
                        if (!serverPlayerEntity.isSpectator() && !serverPlayerEntity.isCreative()){
                            commandContext.getSource().sendMessage(Text.literal("Triggered global event." ));
                            serverPlayerEntity.getWorld().playSound(null,serverPlayerEntity.getBlockPos(),
                                    SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.MASTER,1f,1f);
                            serverPlayerEntity.networkHandler.sendPacket(new TitleS2CPacket(Text.of("§4||§r§6 All of you have been selected for... §r§4||§r")));
                            RandomBullshit.setTimer(serverPlayerEntity,60, true);
                        }
                    });
                }
                return 0;
            }));
        });
    }
}
