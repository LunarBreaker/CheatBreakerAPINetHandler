package com.cheatbreaker.nethandler.server;

import java.io.*;
import java.util.*;
import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;
import java.beans.*;

public class CBPacketVoiceChannel extends CBPacket
{
    private UUID uuid;
    private String name;
    private Map<UUID, String> players;
    private Map<UUID, String> listening;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeUUID(this.uuid);
        b.writeString(this.name);
        this.writeMap(b, this.players);
        this.writeMap(b, this.listening);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.uuid = b.readUUID();
        this.name = b.readString();
        this.players = this.readMap(b);
        this.listening = this.readMap(b);
    }

    private void writeMap(ByteBufWrapper b, Map<UUID, String> players) {
        b.writeVarInt(players.size());
        for (Map.Entry<UUID, String> uuidStringEntry : players.entrySet()) {
            b.writeUUID(uuidStringEntry.getKey());
            b.writeString(uuidStringEntry.getValue());
        }
    }

    private Map<UUID, String> readMap(ByteBufWrapper b) {
        int size = b.readVarInt();
        Map<UUID, String> players = new HashMap<UUID, String>();
        for (int i = 0; i < size; ++i) {
            UUID uuid = b.readUUID();
            String name = b.readString();
            players.put(uuid, name);
        }
        return players;
    }

    @Override
    public void process(ICBNetHandler handler) {
        ((ICBNetHandlerClient)handler).handleVoiceChannels(this);
    }

    @ConstructorProperties({ "uuid", "name", "players", "listening" })
    public CBPacketVoiceChannel(UUID uuid, String name, Map<UUID, String> players, Map<UUID, String> listening) {
        this.uuid = uuid;
        this.name = name;
        this.players = players;
        this.listening = listening;
    }

    public CBPacketVoiceChannel() {
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }

    public Map<UUID, String> getPlayers() {
        return this.players;
    }

    public Map<UUID, String> getListening() {
        return this.listening;
    }
}
