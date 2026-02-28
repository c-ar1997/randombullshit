package random.bullshit.car.networking;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record HypercamPayloadS2C(BlockPos blockPos) implements CustomPayload {
    public static final CustomPayload.Id<HypercamPayloadS2C> ID = new CustomPayload.Id<>(RBNetworkingConstants.HYPERCAM_ID);
    public static final PacketCodec<RegistryByteBuf, HypercamPayloadS2C> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, HypercamPayloadS2C::blockPos, HypercamPayloadS2C::new);


    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
