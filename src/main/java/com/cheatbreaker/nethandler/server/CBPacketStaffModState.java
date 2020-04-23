package com.cheatbreaker.nethandler.server;

import java.io.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;
import java.beans.*;

public class CBPacketStaffModState extends CBPacket
{
    private String mod;
    private boolean state;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(this.mod);
        b.buf().writeBoolean(this.state);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.mod = b.readString();
        this.state = b.buf().readBoolean();
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleStaffModState(this);
    }

    public String getMod() {
        return this.mod;
    }

    public boolean isState() {
        return this.state;
    }

    @ConstructorProperties({ "mod", "state" })
    public CBPacketStaffModState(String mod, boolean state) {
        this.mod = mod;
        this.state = state;
    }

    public CBPacketStaffModState() {
    }
}
