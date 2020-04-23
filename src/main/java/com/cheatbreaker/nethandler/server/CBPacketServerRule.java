package com.cheatbreaker.nethandler.server;

import com.cheatbreaker.nethandler.obj.*;
import java.io.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;

public class CBPacketServerRule extends CBPacket
{
    private ServerRule rule;
    private int intValue;
    private float floatValue;
    private boolean booleanValue;
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
        this.stringValue = "";
        this.rule = rule;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeString(this.rule.getRule());
        b.buf().writeBoolean(this.booleanValue);
        b.buf().writeInt(this.intValue);
        b.buf().writeFloat(this.floatValue);
        b.writeString(this.stringValue);
    }

    @Override
    protected byte[] readBlob(ByteBufWrapper b) {
        return super.readBlob(b);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.rule = ServerRule.getRule(b.readString());
        this.booleanValue = b.buf().readBoolean();
        this.intValue = b.buf().readInt();
        this.floatValue = b.buf().readFloat();
        this.stringValue = b.readString();
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleServerRule(this);
    }

    public ServerRule getRule() {
        return this.rule;
    }

    public int getIntValue() {
        return this.intValue;
    }

    public float getFloatValue() {
        return this.floatValue;
    }

    public boolean isBooleanValue() {
        return this.booleanValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }
}
