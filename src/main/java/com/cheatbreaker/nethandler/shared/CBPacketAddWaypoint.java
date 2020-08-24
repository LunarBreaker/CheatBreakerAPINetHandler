package com.cheatbreaker.nethandler.shared;

import com.cheatbreaker.nethandler.ByteBufWrapper;
import com.cheatbreaker.nethandler.CBPacket;
import com.cheatbreaker.nethandler.ICBNetHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor @NoArgsConstructor
public class CBPacketAddWaypoint extends CBPacket {

    @Getter
    private String name;
    @Getter
    private String world;
    @Getter
    private int color;
    @Getter
    private int x;
    @Getter
    private int y;
    @Getter
    private int z;
    @Getter
    private boolean forced;
    @Getter
    private boolean visible;

    @Override
    public void write(ByteBufWrapper out) throws IOException {
        out.writeString(this.name);
        out.writeString(this.world);
        out.buf().writeInt(this.color);
        out.buf().writeInt(this.x);
        out.buf().writeInt(this.y);
        out.buf().writeInt(this.z);
        out.buf().writeBoolean(this.forced);
        out.buf().writeBoolean(this.visible);
    }

    @Override
    public void read(ByteBufWrapper in) throws IOException {
        this.name = in.readString();
        this.world = in.readString();
        this.color = in.buf().readInt();
        this.x = in.buf().readInt();
        this.y = in.buf().readInt();
        this.z = in.buf().readInt();
        this.forced = in.buf().readBoolean();
        this.visible = in.buf().readBoolean();
    }

    @Override
    public void process(ICBNetHandler handler) {
        handler.handleAddWaypoint(this);
    }

}
