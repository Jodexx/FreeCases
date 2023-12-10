package com.jodexindustries.freecases;

public class Utils {
    public boolean isUsed(String player) {
        return !CustomConfig.getData().getStringList("Used").contains(player);
    }
}
