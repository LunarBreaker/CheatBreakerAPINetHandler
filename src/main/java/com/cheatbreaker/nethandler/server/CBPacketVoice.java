package com.cheatbreaker.nethandler.server;

import java.util.*;
import java.io.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;
import java.beans.*;

public class CBPacketVoice extends CBPacket
{
    private UUID uuid;
    private byte[] data;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeUUID(this.uuid);
        this.writeBlob(b, this.data);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.uuid = b.readUUID();
        this.data = this.readBlob(b);
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleVoice(this);
    }

    @ConstructorProperties({ "uuid", "data" })
    public CBPacketVoice(UUID uuid, byte[] data) {
        this.uuid = uuid;
        this.data = data;
    }

    public CBPacketVoice() {
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public byte[] getData() {
        return this.data;
    }
}
