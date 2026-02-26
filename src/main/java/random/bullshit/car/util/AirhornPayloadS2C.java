package random.bullshit.car.util;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record AirhornPayloadS2C(BlockPos blockPos) implements CustomPayload {
    public static final CustomPayload.Id<AirhornPayloadS2C> ID = new CustomPayload.Id<>(RBNetworkingConstants.AIRHORN_ID);
    public static final PacketCodec<RegistryByteBuf, AirhornPayloadS2C> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, AirhornPayloadS2C::blockPos, AirhornPayloadS2C::new);


    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
