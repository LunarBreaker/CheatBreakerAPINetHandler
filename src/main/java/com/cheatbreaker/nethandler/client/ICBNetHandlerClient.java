package com.cheatbreaker.nethandler.client;

import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.server.*;

public interface ICBNetHandlerClient extends ICBNetHandler
{
    void handleGhost(CBPacketGhost p0);

    void handleCooldown(CBPacketCooldown p0);

    void handleNotification(CBPacketNotification p0);

    void handleStaffModState(CBPacketStaffModState p0);

    void handleNametagsUpdate(CBPacketNametagsUpdate p0);

    void handleTeammates(CBPacketTeammates p0);

    void handleOverrideNametags(CBPacketNametagsOverride p0);

    void handleAddHologram(CBPacketHologram p0);

    void handleUpdateHologram(CBPacketHologramUpdate p0);

    void handleRemoveHologram(CBPacketRemoveHologram p0);

    void handleRemoveHologram(CBPacketHologramRemove p0);

    void handleTitle(CBPacketTitle p0);

    void handleServerRule(CBPacketServerRule p0);

    void handleVoice(CBPacketVoice p0);

    void handleVoice(CBPacketClientVoice p0);

    void handleVoiceChannels(CBPacketVoiceChannel p0);

    void handleVoiceChannelUpdate(CBPacketVoiceChannelUpdate p0);

    void handleVoiceChannelDelete(CBPacketVoiceChannelRemove p0);

    void handleUpdateWorld(CBPacketUpdateWorld p0);

    void handleServerUpdate(CBPacketServerUpdate p0);

    void handleWorldBorder(CBPacketWorldBorder p0);

    void handleWorldBorderUpdate(CBPacketWorldBorderUpdate p0);

    void handleWorldBorderRemove(CBPacketWorldBorderRemove p0);
}
