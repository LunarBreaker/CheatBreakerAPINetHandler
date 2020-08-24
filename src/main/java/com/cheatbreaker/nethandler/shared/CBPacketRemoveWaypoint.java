package com.cheatbreaker.nethandler.shared;

import com.cheatbreaker.nethandler.ByteBufWrapper;
import com.cheatbreaker.nethandler.CBPacket;
import com.cheatbreaker.nethandler.ICBNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor @NoArgsConstructor
public class CBPacketRemoveWaypoint extends CBPacket {

    @Getter
    private String name;
    @Getter
    private String world;

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

}
