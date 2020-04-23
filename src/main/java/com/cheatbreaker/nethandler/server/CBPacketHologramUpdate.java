package com.cheatbreaker.nethandler.server;

import java.io.*;
import java.util.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;

public class CBPacketHologramUpdate extends CBPacket
{
    private UUID uuid;
    private List<String> lines;

    public CBPacketHologramUpdate() {
    }

    public CBPacketHologramUpdate(UUID uuid, List<String> lines) {
        this.uuid = uuid;
        this.lines = lines;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeUUID(this.uuid);
        b.writeVarInt(this.lines.size());
        for (String s : this.lines) {
            b.writeString(s);
        }
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.uuid = b.readUUID();
        int linesSize = b.readVarInt();
        this.lines = new ArrayList<String>();
        for (int i = 0; i < linesSize; ++i) {
            this.lines.add(b.readString());
        }
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleUpdateHologram(this);
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public List<String> getLines() {
        return this.lines;
    }
}
