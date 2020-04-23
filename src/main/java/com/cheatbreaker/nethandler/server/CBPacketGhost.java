package com.cheatbreaker.nethandler.server;

import java.beans.ConstructorProperties;
import java.io.*;
import java.util.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;

public class CBPacketGhost extends CBPacket
{
    private List<UUID> uuidList;

    public CBPacketGhost() {
    }

    @ConstructorProperties({ "uuidList" })
    public CBPacketGhost(List<UUID> uuidList) {
        this.uuidList = uuidList;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeVarInt(this.uuidList.size());
        for (UUID uuid : this.uuidList) {
            b.writeUUID(uuid);
        }
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        int uuidListSize = b.readVarInt();
        this.uuidList = new ArrayList<>();
        for (int i = 0; i < uuidListSize; ++i) {
            this.uuidList.add(b.readUUID());
        }
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleGhost(this);
    }

    public List<UUID> getUuidList() {
        return this.uuidList;
    }
}
