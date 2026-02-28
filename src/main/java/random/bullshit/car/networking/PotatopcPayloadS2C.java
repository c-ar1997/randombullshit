package random.bullshit.car.networking;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record PotatopcPayloadS2C(BlockPos blockPos)implements CustomPayload {
    public static final CustomPayload.Id<PotatopcPayloadS2C> ID = new CustomPayload.Id<>(RBNetworkingConstants.POTATOPC_ID);
    public static final PacketCodec<RegistryByteBuf, PotatopcPayloadS2C> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, PotatopcPayloadS2C::blockPos, PotatopcPayloadS2C::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
