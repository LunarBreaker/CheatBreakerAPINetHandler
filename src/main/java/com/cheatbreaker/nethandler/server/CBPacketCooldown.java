package com.cheatbreaker.nethandler.server;

import java.io.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;

public class CBPacketCooldown extends CBPacket
{
    private String message;
    private long durationMs;
    private int iconId;

    public CBPacketCooldown() {
    }

    public CBPacketCooldown(String message, long durationMs, int iconId) {
        this.message = message;
        this.durationMs = durationMs;
        this.iconId = iconId;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(this.message);
        b.buf().writeLong(this.durationMs);
        b.buf().writeInt(this.iconId);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.message = b.readString();
        this.durationMs = b.buf().readLong();
        this.iconId = b.buf().readInt();
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleCooldown(this);
    }

    public String getMessage() {
        return this.message;
    }

    public long getDurationMs() {
        return this.durationMs;
    }

    public int getIconId() {
        return this.iconId;
    }
}
