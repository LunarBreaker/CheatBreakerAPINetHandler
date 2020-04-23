package com.cheatbreaker.nethandler.server;

import java.util.*;
import java.io.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;

public class CBPacketRemoveHologram extends CBPacket
{
    private UUID uuid;

    public CBPacketRemoveHologram() {
    }

    public CBPacketRemoveHologram(UUID uuid) {
        this.uuid = uuid;
    }

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
        ((ICBNetHandlerClient)handler).handleRemoveHologram(this);
    }

    public UUID getUuid() {
        return this.uuid;
    }
}
