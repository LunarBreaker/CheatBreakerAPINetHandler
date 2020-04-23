package com.cheatbreaker.nethandler.server;

import java.io.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;
import java.beans.*;

public class CBPacketWorldBorderRemove extends CBPacket
{
    private String id;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(this.id);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.id = b.readString();
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleWorldBorderRemove(this);
    }

    @ConstructorProperties({ "id" })
    public CBPacketWorldBorderRemove(String id) {
        this.id = id;
    }

    public CBPacketWorldBorderRemove() {
    }

    public String getId() {
        return this.id;
    }
}
