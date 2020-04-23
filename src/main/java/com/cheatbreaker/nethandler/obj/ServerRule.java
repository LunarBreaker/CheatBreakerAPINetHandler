package com.cheatbreaker.nethandler.obj;

public enum ServerRule
{
    VOICE_ENABLED("voiceEnabled", Boolean.class),
    MINIMAP_STATUS("minimapStatus", String.class),
    SERVER_HANDLES_WAYPOINTS("serverHandlesWaypoints", Boolean.class),
    COMPETITIVE_GAMEMODE("competitiveGame", Boolean.class);

    String rule;
    Class value;

    private ServerRule(String rule, Class clazz) {
        this.rule = rule;
        this.value = clazz;
    }

    public static ServerRule getRule(String s) {
        ServerRule rule = null;
        ServerRule[] var2 = values();
        for (ServerRule sr : var2) {
            if (sr.getRule().equals(s)) {
                rule = sr;
            }
        }
        return rule;
    }

    public String getRule() {
        return this.rule;
    }

    public Class getValue() {
        return this.value;
    }
}
