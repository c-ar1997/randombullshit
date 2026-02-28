package random.bullshit.car.networking;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record HellenKellerPayloadS2C(BlockPos blockPos) implements CustomPayload {
    public static final CustomPayload.Id<HellenKellerPayloadS2C> ID = new CustomPayload.Id<>(RBNetworkingConstants.HELLEN_KELLER_ID);
    public static final PacketCodec<RegistryByteBuf, HellenKellerPayloadS2C> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, HellenKellerPayloadS2C::blockPos, HellenKellerPayloadS2C::new);


    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
