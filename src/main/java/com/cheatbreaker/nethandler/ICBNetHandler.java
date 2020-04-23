package com.cheatbreaker.nethandler;

import com.cheatbreaker.nethandler.shared.*;

public interface ICBNetHandler
{
    void handleAddWaypoint(CBPacketWaypointAdd p0);

    void handleRemoveWaypoint(CBPacketWaypointRemove p0);
}
