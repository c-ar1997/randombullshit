package random.bullshit.car.util;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record CrashPayloadS2C(BlockPos blockPos) implements CustomPayload {
    public static final CustomPayload.Id<CrashPayloadS2C> ID = new CustomPayload.Id<>(RBNetworkingConstants.CRASH_ID);
    public static final PacketCodec<RegistryByteBuf, CrashPayloadS2C> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, CrashPayloadS2C::blockPos, CrashPayloadS2C::new);


    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
