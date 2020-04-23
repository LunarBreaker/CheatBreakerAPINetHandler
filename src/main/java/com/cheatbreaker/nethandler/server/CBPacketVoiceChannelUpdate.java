package com.cheatbreaker.nethandler.server;

import java.util.*;
import java.io.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;
import java.beans.*;

public class CBPacketVoiceChannelUpdate extends CBPacket
{
    public int status;
    private UUID channelUuid;
    private UUID uuid;
    private String name;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeVarInt(this.status);
        b.writeUUID(this.channelUuid);
        b.writeUUID(this.uuid);
        b.writeString(this.name);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.status = b.readVarInt();
        this.channelUuid = b.readUUID();
        this.uuid = b.readUUID();
        this.name = b.readString();
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleVoiceChannelUpdate(this);
    }

    @ConstructorProperties({ "status", "channelUuid", "uuid", "name" })
    public CBPacketVoiceChannelUpdate(int status, UUID channelUuid, UUID uuid, String name) {
        this.status = status;
        this.channelUuid = channelUuid;
        this.uuid = uuid;
        this.name = name;
    }

    public CBPacketVoiceChannelUpdate() {
    }

    public int getStatus() {
        return this.status;
    }

    public UUID getChannelUuid() {
        return this.channelUuid;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }
}
