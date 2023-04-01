package com.jodexindustries.freecases;

public class Utils {
    public boolean CheckUsage(String player) {
        return CustomConfig.getData().getStringList("Used").contains(player);
    }
}
