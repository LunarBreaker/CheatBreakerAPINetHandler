package com.cheatbreaker.nethandler.server;

import com.cheatbreaker.nethandler.ByteBufWrapper;
import com.cheatbreaker.nethandler.CBPacket;
import com.cheatbreaker.nethandler.ICBNetHandler;
import com.cheatbreaker.nethandler.client.ICBNetHandlerClient;
import com.cheatbreaker.nethandler.obj.ServerRule;
import lombok.Getter;

import java.io.IOException;

public class CBPacketServerRule extends CBPacket {

    @Getter
    private ServerRule rule;
    @Getter
    private int intValue;
    @Getter
    private float floatValue;
    @Getter
    private boolean booleanValue;
    @Getter
    private String stringValue;

    public CBPacketServerRule() {
        this.stringValue = "";
    }

    public CBPacketServerRule(ServerRule rule, float value) {
        this(rule);
        this.floatValue = value;
    }

    public CBPacketServerRule(ServerRule rule, boolean value) {
        this(rule);
        this.booleanValue = value;
    }

    public CBPacketServerRule(ServerRule rule, int value) {
        this(rule);
        this.intValue = value;
    }

    public CBPacketServerRule(ServerRule rule, String value) {
        this(rule);
        this.stringValue = value;
    }

    private CBPacketServerRule(ServerRule rule) {
        this.rule = rule;
        this.stringValue = "";
    }

    @Override
    public void write(ByteBufWrapper out) throws IOException {
        out.writeString(this.rule.getRule());
        out.buf().writeBoolean(this.booleanValue);
        out.buf().writeInt(this.intValue);
        out.buf().writeFloat(this.floatValue);
        out.writeString(this.stringValue);
    }

    @Override
    public void read(ByteBufWrapper in) throws IOException {
        this.rule = ServerRule.getRule(in.readString());
        this.booleanValue = in.buf().readBoolean();
        this.intValue = in.buf().readInt();
        this.floatValue = in.buf().readFloat();
        this.stringValue = in.readString();
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleServerRule(this);
    }

}
