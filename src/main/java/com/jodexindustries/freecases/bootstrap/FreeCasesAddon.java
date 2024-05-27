package com.jodexindustries.freecases.bootstrap;

import com.jodexindustries.donatecase.api.CaseManager;
import com.jodexindustries.donatecase.api.addon.internal.InternalJavaAddon;
import com.jodexindustries.freecases.utils.CustomConfig;
import com.jodexindustries.freecases.utils.Utils;
import org.bukkit.plugin.Plugin;

public final class FreeCasesAddon extends InternalJavaAddon implements FreeCases {
    private CustomConfig config;
    private CaseManager api;
    @Override
    public void onEnable() {
        api = getCaseAPI();
        config = Utils.load(this);
    }
    @Override
    public void onDisable() {
        api.getSubCommandManager().unregisterSubCommand("free");
    }

    @Override
    public CustomConfig getAddonConfig() {
        return config;
    }

    @Override
    public Plugin getPlugin() {
        return getDonateCase();
    }

    @Override
    public CaseManager getAPI() {
        return api;
    }

//    @Override
//    public void saveResource(@NotNull String resourcePath, boolean replace) {
//        super.saveResource(resourcePath, replace);
//    }
}
