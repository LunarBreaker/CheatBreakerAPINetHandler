package com.cheatbreaker.nethandler.server;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;

public class CBPacketNametagsOverride extends CBPacket
{
    private UUID player;
    private List<String> tags;

    public CBPacketNametagsOverride() {
    }

    public CBPacketNametagsOverride(UUID player, List<String> tags) {
        this.player = player;
        this.tags = tags;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeUUID(this.player);
        Iterator<String> iterator = null;
        AtomicReference<String> s = null;
        b.writeOptional(this.tags, t -> {
            b.writeVarInt(t.size());
            t.iterator();
            while (iterator.hasNext()) {
                s.set(iterator.next());
                b.writeString(s.get());
            }
        });
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.player = b.readUUID();
        AtomicInteger tagsSize = new AtomicInteger();
        AtomicReference<ArrayList<String>> tags = null;
        AtomicInteger i = new AtomicInteger();
        this.tags = (List<String>) b.readOptional(() -> {
            tagsSize.set(b.readVarInt());
            tags.set(new ArrayList<String>());
            for (i.set(0); i.get() < tagsSize.get(); i.incrementAndGet()) {
                tags.get().add(b.readString());
            }
            return tags;
        });
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleOverrideNametags(this);
    }

    public UUID getPlayer() {
        return this.player;
    }

    public List<String> getTags() {
        return this.tags;
    }
}
