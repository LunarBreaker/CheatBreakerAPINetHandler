package com.cheatbreaker.nethandler.client;

import java.util.*;
import java.io.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.server.*;

public class CBPacketVoiceChannelSwitch extends CBPacket
{
    private UUID switchingTo;

    public CBPacketVoiceChannelSwitch() {
    }

    public CBPacketVoiceChannelSwitch(UUID switchingTo) {
        this.switchingTo = switchingTo;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeUUID(this.switchingTo);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.switchingTo = b.readUUID();
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerServer)handler).handleVoiceChannelSwitch(this);
    }

    public UUID getSwitchingTo() {
        return this.switchingTo;
    }
}
