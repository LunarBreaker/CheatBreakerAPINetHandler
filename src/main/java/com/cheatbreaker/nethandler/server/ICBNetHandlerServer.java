package com.cheatbreaker.nethandler.server;

import com.cheatbreaker.nethandler.*;
import com.cheatbreaker.nethandler.client.*;

public interface ICBNetHandlerServer extends ICBNetHandler
{
    void handleVoice(CBPacketClientVoice p0);

    void handleVoiceChannelSwitch(CBPacketVoiceChannelSwitch p0);

    void handleVoiceMute(CBPacketVoiceMute p0);
}
