package random.bullshit.car;

import com.ibm.icu.impl.StaticUnicodeSets;
import com.ibm.icu.impl.locale.KeyTypeData;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Keyboard;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.input.KeyCodes;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.GraphicsMode;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.sound.SoundCategory;
import random.bullshit.car.util.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class RandomBullshitClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(HellenKellerPayloadS2C.ID,((payload, context) ->
                context.client().execute(() -> sound(context,0d))));
        ClientPlayNetworking.registerGlobalReceiver(AirhornPayloadS2C.ID,((payload, context) ->
                context.client().execute(() -> sound(context, 100000000d))));
        ClientPlayNetworking.registerGlobalReceiver(HypercamPayloadS2C.ID,((payload, context) ->
                context.client().execute(() -> fps(context,10))));
        ClientPlayNetworking.registerGlobalReceiver(CrashPayloadS2C.ID,((payload, context) ->
                context.client().execute(() -> context.client().close())));
        ClientPlayNetworking.registerGlobalReceiver(WindowPayloadS2C.ID,((payload, context) ->
                context.client().execute(() -> context.client().getWindow().setWindowedSize(100,100))));
        ClientPlayNetworking.registerGlobalReceiver(DrunkPayloadS2C.ID,((payload, context) ->
                context.client().execute(() -> invert(context)
                )));
        ClientPlayNetworking.registerGlobalReceiver(ChoppedPayloadS2C.ID,((payload, context) ->
                context.client().execute(() -> chopped(context))));
        ClientPlayNetworking.registerGlobalReceiver(PotatopcPayloadS2C.ID,((payload, context) ->
                context.client().execute(() -> potatopc(context))));
    }
    public void invert(ClientPlayNetworking.Context context){
                context.client().options.forwardKey.setBoundKey(InputUtil.fromTranslationKey("key.keyboard.s"));
                context.client().options.backKey.setBoundKey(InputUtil.fromTranslationKey("key.keyboard.w"));
                context.client().options.leftKey.setBoundKey(InputUtil.fromTranslationKey("key.keyboard.d"));
                context.client().options.rightKey.setBoundKey(InputUtil.fromTranslationKey("key.keyboard.a"));
                KeyBinding.updateKeysByCode();
                context.client().options.write();
    }

    // make a chopped method to remove the outer layer
    public void chopped(ClientPlayNetworking.Context context){
        context.client().options.togglePlayerModelPart(PlayerModelPart.CAPE, false);
        context.client().options.togglePlayerModelPart(PlayerModelPart.HAT, false);
        context.client().options.togglePlayerModelPart(PlayerModelPart.RIGHT_PANTS_LEG, false);
        context.client().options.togglePlayerModelPart(PlayerModelPart.LEFT_PANTS_LEG, false);
        context.client().options.togglePlayerModelPart(PlayerModelPart.RIGHT_SLEEVE, false);
        context.client().options.togglePlayerModelPart(PlayerModelPart.LEFT_SLEEVE, false);
        context.client().options.togglePlayerModelPart(PlayerModelPart.JACKET, false);
        context.client().options.write();
    }

    public void potatopc(ClientPlayNetworking.Context context){
        context.client().options.getGraphicsMode().setValue(GraphicsMode.FAST);
        context.client().options.getMaxFps().setValue(20);
        context.client().options.getCloudRenderMode().setValue(CloudRenderMode.OFF);
        context.client().options.getViewDistance().setValue(2);
        context.client().options.getSimulationDistance().setValue(5);
        context.client().options.getParticles().setValue(ParticlesMode.MINIMAL);
        context.client().options.getEntityDistanceScaling().setValue(0.1d);
        context.client().options.write();
    }

    public void sound(ClientPlayNetworking.Context context, double sound){
        context.client().options.getSoundVolumeOption(SoundCategory.MASTER).setValue(sound);
        context.client().options.write();
    }

    public void fps(ClientPlayNetworking.Context context, int fps){
        context.client().options.getMaxFps().setValue(fps);
        context.client().options.write();
    }
}
