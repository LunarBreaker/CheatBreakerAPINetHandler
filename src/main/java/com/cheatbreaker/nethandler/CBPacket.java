package com.cheatbreaker.nethandler;

import org.bukkit.entity.*;
import java.io.*;
import io.netty.buffer.*;
import com.google.common.collect.*;
import com.cheatbreaker.nethandler.shared.*;
import com.cheatbreaker.nethandler.client.*;
import com.cheatbreaker.nethandler.server.*;

public abstract class CBPacket
{
    private static BiMap<Class, Integer> REGISTRY;
    private Player attachment;

    public static CBPacket handle(byte[] data) {
        return handle(data, null);
    }

    public static CBPacket handle(byte[] data, Player attachment) {
        ByteBufWrapper wrappedBuffer = new ByteBufWrapper(Unpooled.wrappedBuffer(data));
        int packetId = wrappedBuffer.readVarInt();
        Class packetClass = CBPacket.REGISTRY.inverse().get(packetId);
        if (packetClass != null) {
            try {
                CBPacket packet = (CBPacket) CBPacket.REGISTRY.inverse().get(packetId).newInstance();
                packet.attach(attachment);
                packet.read(wrappedBuffer);
                return packet;
            }
            catch (InstantiationException | IllegalAccessException | IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return null;
    }

    public static byte[] getPacketData(CBPacket packet) {
        ByteBufWrapper wrappedBuffer = new ByteBufWrapper(Unpooled.buffer());
        wrappedBuffer.writeVarInt(CBPacket.REGISTRY.get(packet.getClass()));
        try {
            packet.write(wrappedBuffer);
        }
        catch (IOException var3) {
            var3.printStackTrace();
        }
        return wrappedBuffer.buf().array();
    }

    public static ByteBuf getPacketBuf(CBPacket packet) {
        ByteBufWrapper wrappedBuffer = new ByteBufWrapper(Unpooled.buffer());
        wrappedBuffer.writeVarInt(CBPacket.REGISTRY.get(packet.getClass()));
        try {
            packet.write(wrappedBuffer);
        }
        catch (IOException var3) {
            var3.printStackTrace();
        }
        return wrappedBuffer.buf();
    }

    private static void addPacket(int id, Class clazz) {
        if (CBPacket.REGISTRY.containsKey(clazz)) {
            throw new IllegalArgumentException("Duplicate packet class (" + clazz.getSimpleName() + "), already used by " + CBPacket.REGISTRY.get(clazz));
        }
        if (CBPacket.REGISTRY.containsValue(id)) {
            throw new IllegalArgumentException("Duplicate packet ID (" + id + "), already used by " + (CBPacket.REGISTRY.inverse().get(id)).getSimpleName());
        }
        CBPacket.REGISTRY.put(clazz, id);
    }

    public abstract void write(ByteBufWrapper p0) throws IOException;

    public abstract void read(ByteBufWrapper p0) throws IOException;

    public abstract void process(ICBNetHandler p0);

    protected void writeBlob(ByteBufWrapper b, byte[] bytes) {
        b.buf().writeShort(bytes.length);
        b.buf().writeBytes(bytes);
    }

    protected byte[] readBlob(ByteBufWrapper b) {
        short key = b.buf().readShort();
        if (key < 0) {
            System.out.println("Key was smaller than nothing! Weird key!");
            return null;
        }
        byte[] blob = new byte[key];
        b.buf().readBytes(blob);
        return blob;
    }

    public void attach(Player obj) {
        this.attachment = obj;
    }

    public Player getAttachment() {
        return this.attachment;
    }

    static {
        REGISTRY = HashBiMap.create();
        addPacket(0, CBPacketWaypointAdd.class);
        addPacket(2, CBPacketWaypointRemove.class);
        addPacket(3, CBPacketCooldown.class);
        addPacket(4, CBPacketNotification.class);
        addPacket(5, CBPacketStaffModState.class);
        addPacket(6, CBPacketNametagsUpdate.class);
        addPacket(7, CBPacketTeammates.class);
        addPacket(8, CBPacketNametagsOverride.class);
        addPacket(9, CBPacketHologram.class);
        addPacket(10, CBPacketHologramUpdate.class);
        addPacket(11, CBPacketRemoveHologram.class);
        addPacket(12, CBPacketTitle.class);
        addPacket(14, CBPacketServerRule.class);
        addPacket(15, CBPacketClientVoice.class);
        addPacket(16, CBPacketVoice.class);
        addPacket(17, CBPacketVoiceChannel.class);
        addPacket(18, CBPacketVoiceChannelUpdate.class);
        addPacket(19, CBPacketVoiceMute.class);
        addPacket(20, CBPacketVoiceChannelSwitch.class);
        addPacket(21, CBPacketVoiceChannelRemove.class);
        addPacket(23, CBPacketUpdateWorld.class);
        addPacket(24, CBPacketServerUpdate.class);
        addPacket(25, CBPacketWorldBorder.class);
        addPacket(26, CBPacketWorldBorderUpdate.class);
        addPacket(27, CBPacketWorldBorderRemove.class);
        addPacket(28, CBPacketGhost.class);
    }
}
