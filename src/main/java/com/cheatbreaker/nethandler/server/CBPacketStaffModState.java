package com.cheatbreaker.nethandler.server;

import com.cheatbreaker.nethandler.ByteBufWrapper;
import com.cheatbreaker.nethandler.CBPacket;
import com.cheatbreaker.nethandler.ICBNetHandler;
import com.cheatbreaker.nethandler.client.ICBNetHandlerClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor @NoArgsConstructor
public class CBPacketStaffModState extends CBPacket {

    @Getter
    private String mod;
    @Getter
    private boolean state;

    @Override
    public void write(ByteBufWrapper out) throws IOException {
        out.writeString(this.mod);
        out.buf().writeBoolean(this.state);
    }

    @Override
    public void read(ByteBufWrapper in) throws IOException {
        this.mod = in.readString();
        this.state = in.buf().readBoolean();
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleStaffModState(this);
    }

}
