package com.cheatbreaker.nethandler.server;

import java.io.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;
import java.beans.*;

public class CBPacketWorldBorderUpdate extends CBPacket
{
    private String id;
    private double minX;
    private double minZ;
    private double maxX;
    private double maxZ;
    private int durationTicks;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(this.id);
        b.buf().writeDouble(this.minX);
        b.buf().writeDouble(this.minZ);
        b.buf().writeDouble(this.maxX);
        b.buf().writeDouble(this.maxZ);
        b.buf().writeInt(this.durationTicks);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.id = b.readString();
        this.minX = b.buf().readDouble();
        this.minZ = b.buf().readDouble();
        this.maxX = b.buf().readDouble();
        this.maxZ = b.buf().readDouble();
        this.durationTicks = b.buf().readInt();
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleWorldBorderUpdate(this);
    }

    public String getId() {
        return this.id;
    }

    public double getMinX() {
        return this.minX;
    }

    public double getMinZ() {
        return this.minZ;
    }

    public double getMaxX() {
        return this.maxX;
    }

    public double getMaxZ() {
        return this.maxZ;
    }

    public int getDurationTicks() {
        return this.durationTicks;
    }

    public CBPacketWorldBorderUpdate() {
    }

    @ConstructorProperties({ "id", "minX", "minZ", "maxX", "maxZ", "durationTicks" })
    public CBPacketWorldBorderUpdate(String id, double minX, double minZ, double maxX, double maxZ, int durationTicks) {
        this.id = id;
        this.minX = minX;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxZ = maxZ;
        this.durationTicks = durationTicks;
    }
}
