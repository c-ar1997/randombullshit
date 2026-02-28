package random.bullshit.car.networking;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record DrunkPayloadS2C(BlockPos blockPos) implements CustomPayload {
    public static final CustomPayload.Id<DrunkPayloadS2C> ID = new CustomPayload.Id<>(RBNetworkingConstants.DRUNK_ID);
    public static final PacketCodec<RegistryByteBuf, DrunkPayloadS2C> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, DrunkPayloadS2C::blockPos, DrunkPayloadS2C::new);


    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
