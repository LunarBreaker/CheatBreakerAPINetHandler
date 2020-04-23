package com.cheatbreaker.nethandler.server;

import java.util.*;
import java.io.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;
import java.beans.*;

public class CBPacketVoiceChannelRemove extends CBPacket
{
    private UUID uuid;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeUUID(this.uuid);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.uuid = b.readUUID();
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleVoiceChannelDelete(this);
    }

    @ConstructorProperties({ "uuid" })
    public CBPacketVoiceChannelRemove(UUID uuid) {
        this.uuid = uuid;
    }

    public CBPacketVoiceChannelRemove() {
    }

    public UUID getUuid() {
        return this.uuid;
    }
}
