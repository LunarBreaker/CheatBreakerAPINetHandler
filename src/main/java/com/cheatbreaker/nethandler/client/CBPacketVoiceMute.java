package com.cheatbreaker.nethandler.client;

import java.util.*;
import java.io.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.server.*;

public class CBPacketVoiceMute extends CBPacket
{
    private UUID muting;

    public CBPacketVoiceMute() {
    }

    public CBPacketVoiceMute(UUID muting) {
        this.muting = muting;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeUUID(this.muting);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.muting = b.readUUID();
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerServer)handler).handleVoiceMute(this);
    }

    public UUID getMuting() {
        return this.muting;
    }
}
