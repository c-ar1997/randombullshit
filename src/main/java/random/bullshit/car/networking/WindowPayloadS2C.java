package random.bullshit.car.networking;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record WindowPayloadS2C(BlockPos blockPos) implements CustomPayload {
    public static final CustomPayload.Id<WindowPayloadS2C> ID = new CustomPayload.Id<>(RBNetworkingConstants.WINDOW_ID);
    public static final PacketCodec<RegistryByteBuf, WindowPayloadS2C> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, WindowPayloadS2C::blockPos, WindowPayloadS2C::new);


    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
