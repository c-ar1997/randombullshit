package random.bullshit.car.util;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record ChoppedPayloadS2C(BlockPos blockPos) implements CustomPayload {
    public static final CustomPayload.Id<ChoppedPayloadS2C> ID = new ChoppedPayloadS2C.Id<>(RBNetworkingConstants.CHOPPED_ID);
    public static final PacketCodec<RegistryByteBuf, ChoppedPayloadS2C> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, ChoppedPayloadS2C::blockPos, ChoppedPayloadS2C::new);
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
