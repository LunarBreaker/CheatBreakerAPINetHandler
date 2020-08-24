package com.cheatbreaker.nethandler.client;

import com.cheatbreaker.nethandler.ByteBufWrapper;
import com.cheatbreaker.nethandler.CBPacket;
import com.cheatbreaker.nethandler.ICBNetHandler;
import com.cheatbreaker.nethandler.server.ICBNetHandlerServer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor
public class CBPacketVoiceChannelSwitch extends CBPacket {

    @Getter
    private UUID switchingTo;

    @Override
    public void write(ByteBufWrapper out) throws IOException {
        out.writeUUID(this.switchingTo);
    }

    @Override
    public void read(ByteBufWrapper in) throws IOException {
        this.switchingTo = in.readUUID();
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerServer)handler).handleVoiceChannelSwitch(this);
    }

}
