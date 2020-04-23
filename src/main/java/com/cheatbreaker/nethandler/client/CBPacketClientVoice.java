package com.cheatbreaker.nethandler.client;

import java.io.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.server.*;

public class CBPacketClientVoice extends CBPacket
{
    private byte[] data;

    public CBPacketClientVoice() {
    }

    public CBPacketClientVoice(byte[] data) {
        this.data = data;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        this.writeBlob(b, this.data);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.data = this.readBlob(b);
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerServer)handler).handleVoice(this);
        ((ICBNetHandlerClient)handler).handleVoice(this);
    }

    public byte[] getData() {
        return this.data;
    }
}
