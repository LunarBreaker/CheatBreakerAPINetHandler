package com.cheatbreaker.nethandler.server;

import java.io.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;
import java.beans.*;

public class CBPacketTitle extends CBPacket
{
    private String type;
    private String message;
    private float scale;
    private long displayTimeMs;
    private long fadeInTimeMs;
    private long fadeOutTimeMs;

    public CBPacketTitle(String type, String message, long displayTimeMs, long fadeInTimeMs, long fadeOutTimeMs) {
        this(type, message, 1.0f, displayTimeMs, fadeInTimeMs, fadeOutTimeMs);
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(this.type);
        b.writeString(this.message);
        b.buf().writeFloat(this.scale);
        b.buf().writeLong(this.displayTimeMs);
        b.buf().writeLong(this.fadeInTimeMs);
        b.buf().writeLong(this.fadeOutTimeMs);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.type = b.readString();
        this.message = b.readString();
        this.scale = b.buf().readFloat();
        this.displayTimeMs = b.buf().readLong();
        this.fadeInTimeMs = b.buf().readLong();
        this.fadeOutTimeMs = b.buf().readLong();
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleTitle(this);
    }

    public String getType() {
        return this.type;
    }

    public String getMessage() {
        return this.message;
    }

    public float getScale() {
        return this.scale;
    }

    public long getDisplayTimeMs() {
        return this.displayTimeMs;
    }

    public long getFadeInTimeMs() {
        return this.fadeInTimeMs;
    }

    public long getFadeOutTimeMs() {
        return this.fadeOutTimeMs;
    }

    @ConstructorProperties({ "type", "message", "scale", "displayTimeMs", "fadeInTimeMs", "fadeOutTimeMs" })
    public CBPacketTitle(String type, String message, float scale, long displayTimeMs, long fadeInTimeMs, long fadeOutTimeMs) {
        this.type = type;
        this.message = message;
        this.scale = scale;
        this.displayTimeMs = displayTimeMs;
        this.fadeInTimeMs = fadeInTimeMs;
        this.fadeOutTimeMs = fadeOutTimeMs;
    }

    public CBPacketTitle() {
    }
}
