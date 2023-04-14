package ru.whbex.reprotect_stub.mixin;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import net.fabricmc.fabric.api.client.networking.v1.C2SPlayChannelEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.whbex.reprotect_stub.Main;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Mixin({net.minecraft.network.ClientConnection.class})
public abstract class ConnectionMixin {
    @Shadow public abstract void send(Packet<?> packet);

    private static final Identifier CHANNEL_ID = Identifier.of(Main.REPROTECT_NAMESPACE, Main.REPROTECT_PATH);
    @Inject(
            at = @At("HEAD"),
            method = {"channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/packet/Packet;)V"}

    )
    public void readChannel0(ChannelHandlerContext context, Packet<?> packet, CallbackInfo cbInfo){
        if(!(packet instanceof CustomPayloadS2CPacket))
            return;
        Main.LOGGER.info("we're in mixin");
        Identifier id = ((CustomPayloadS2CPacket) packet).getChannel();
        Main.LOGGER.info("packet id: " + id);
        if(!(id.getNamespace().equalsIgnoreCase(CHANNEL_ID.getNamespace())))
            return;
        switch(id.getPath()){
            case "get_serial" -> sendPacket();
            default -> Main.LOGGER.info("Unknown packet channel {}", id);
        }


    }
    private void sendPacket(){
        ByteBuf buf = Unpooled.buffer();
        String out = Main.HASH + " " + Main.REPROTECT_VERSION;
        Main.LOGGER.info("Sending string: " + out);
        buf.writeInt(out.getBytes().length);
        buf.writeBytes(out.getBytes());
        this.send(new CustomPayloadC2SPacket(CHANNEL_ID, new PacketByteBuf(buf)));
    }
}
