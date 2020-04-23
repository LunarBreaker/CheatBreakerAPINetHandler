package com.cheatbreaker.nethandler.server;

import java.io.*;
import java.util.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;
import java.beans.*;

public class CBPacketTeammates extends CBPacket
{
    private UUID leader;
    private long lastMs;
    private Map<UUID, Map<String, Double>> players;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.buf().writeBoolean(this.leader != null);
        if (this.leader != null) {
            b.writeUUID(this.leader);
        }
        b.buf().writeLong(this.lastMs);
        b.writeVarInt(this.players.values().size());
        for (Map.Entry<UUID, Map<String, Double>> entry : this.players.entrySet()) {
            b.writeUUID(entry.getKey());
            b.writeVarInt(entry.getValue().values().size());
            for (Map.Entry<String, Double> stringDoubleEntry : entry.getValue().entrySet()) {
                b.writeString(stringDoubleEntry.getKey());
                b.buf().writeDouble(stringDoubleEntry.getValue());
            }
        }
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        boolean hasLeader = b.buf().readBoolean();
        if (hasLeader) {
            this.leader = b.readUUID();
        }
        this.lastMs = b.buf().readLong();
        int playersSize = b.readVarInt();
        this.players = new HashMap<UUID, Map<String, Double>>();
        for (int i = 0; i < playersSize; ++i) {
            UUID uuid = b.readUUID();
            int posMapSize = b.readVarInt();
            Map<String, Double> posMap = new HashMap<String, Double>();
            for (int j = 0; j < posMapSize; ++j) {
                String key = b.readString();
                double val = b.buf().readDouble();
                posMap.put(key, val);
            }
            this.players.put(uuid, posMap);
        }
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleTeammates(this);
    }

    public UUID getLeader() {
        return this.leader;
    }

    public long getLastMs() {
        return this.lastMs;
    }

    public Map<UUID, Map<String, Double>> getPlayers() {
        return this.players;
    }

    @ConstructorProperties({ "leader", "lastMs", "players" })
    public CBPacketTeammates(UUID leader, long lastMs, Map<UUID, Map<String, Double>> players) {
        this.leader = leader;
        this.lastMs = lastMs;
        this.players = players;
    }

    public CBPacketTeammates() {
    }
}
