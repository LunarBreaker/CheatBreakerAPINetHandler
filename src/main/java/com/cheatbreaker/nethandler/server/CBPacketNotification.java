package com.cheatbreaker.nethandler.server;

import java.io.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;

public class CBPacketNotification extends CBPacket
{
    private String message;
    private long durationMs;
    private String level;

    public CBPacketNotification() {
    }

    public CBPacketNotification(String message, long durationMs, String level) {
        this.message = message;
        this.durationMs = durationMs;
        this.level = level;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(this.message);
        b.buf().writeLong(this.durationMs);
        b.writeString(this.level);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.message = b.readString();
        this.durationMs = b.buf().readLong();
        this.level = b.readString();
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleNotification(this);
    }

    public String getMessage() {
        return this.message;
    }

    public long getDurationMs() {
        return this.durationMs;
    }

    public String getLevel() {
        return this.level;
    }
}
