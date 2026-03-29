package spooketti.streetminer.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import spooketti.streetminer.Streetminer;

public record Test(BlockPos pos) implements CustomPacketPayload {
    public static final Identifier SUMMON_LIGHTNING_PAYLOAD_ID = Identifier.fromNamespaceAndPath(Streetminer.MOD_ID, "summon_lightning");
    public static final CustomPacketPayload.Type<Test> ID = new CustomPacketPayload.Type<>(SUMMON_LIGHTNING_PAYLOAD_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, Test> CODEC = StreamCodec.composite(BlockPos.STREAM_CODEC, Test::pos, Test::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}