package com.cheatbreaker.nethandler.server;

import java.io.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;
import java.beans.*;

public class CBPacketServerUpdate extends CBPacket
{
    private String server;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(this.server);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.server = b.readString();
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleServerUpdate(this);
    }

    @ConstructorProperties({ "server" })
    public CBPacketServerUpdate(String server) {
        this.server = server;
    }

    public CBPacketServerUpdate() {
    }

    public String getServer() {
        return this.server;
    }
}
