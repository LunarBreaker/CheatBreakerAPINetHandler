package com.cheatbreaker.nethandler.shared;

import com.google.common.base.*;
import java.io.*;
import com.cheatbreaker.nethandler.*;

public class CBPacketWaypointRemove extends CBPacket
{
    private String name;
    private String world;

    public CBPacketWaypointRemove(String name, String world) {
        this.name = (String)Preconditions.checkNotNull((Object)name, "name");
        this.world = (String)Preconditions.checkNotNull((Object)world, "world");
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(this.name);
        b.writeString(this.world);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.name = b.readString();
        this.world = b.readString();
    }

    @Override
    public void process(ICBNetHandler handler) {
        handler.handleRemoveWaypoint(this);
    }

    public String getName() {
        return this.name;
    }

    public String getWorld() {
        return this.world;
    }

    public CBPacketWaypointRemove() {
    }
}
