package com.cheatbreaker.nethandler.server;

import java.io.*;
import java.util.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;

public class CBPacketNametagsUpdate extends CBPacket
{
    private Map<UUID, List<String>> playersMap;

    public CBPacketNametagsUpdate() {
    }

    public CBPacketNametagsUpdate(Map<UUID, List<String>> playersMap) {
        this.playersMap = playersMap;
    }

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeVarInt((this.playersMap == null) ? -1 : this.playersMap.size());
        if (this.playersMap != null) {
            for (Map.Entry<UUID, List<String>> uuidListEntry : this.playersMap.entrySet()) {
                UUID uuid = uuidListEntry.getKey();
                List<String> tags = uuidListEntry.getValue();
                b.writeUUID(uuid);
                b.writeVarInt(tags.size());
                for (String s : tags) {
                    b.writeString(s);
                }
            }
        }
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        int playersMapSize = b.readVarInt();
        if (playersMapSize == -1) {
            this.playersMap = null;
        }
        else {
            this.playersMap = new HashMap<UUID, List<String>>();
            for (int i = 0; i < playersMapSize; ++i) {
                UUID uuid = b.readUUID();
                int tagsSize = b.readVarInt();
                List<String> tags = new ArrayList<String>();
                for (int j = 0; j < tagsSize; ++j) {
                    tags.add(b.readString());
                }
                this.playersMap.put(uuid, tags);
            }
        }
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleNametagsUpdate(this);
    }

    public Map<UUID, List<String>> getPlayersMap() {
        return this.playersMap;
    }
}
