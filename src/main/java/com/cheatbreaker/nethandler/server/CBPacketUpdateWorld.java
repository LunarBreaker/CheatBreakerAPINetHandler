package com.cheatbreaker.nethandler.server;

import java.io.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;
import java.beans.*;

public class CBPacketUpdateWorld extends CBPacket
{
    private String world;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(this.world);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.world = b.readString();
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleUpdateWorld(this);
    }

    @ConstructorProperties({ "world" })
    public CBPacketUpdateWorld(String world) {
        this.world = world;
    }

    public CBPacketUpdateWorld() {
    }

    public String getWorld() {
        return this.world;
    }
}
