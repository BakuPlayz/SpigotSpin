package com.github.bakuplayz.spigotspin;

import com.github.bakuplayz.spigotspin.abstraction.menu.listeners.MenuListener;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public final class SpigotSpin {

    public static final Logger LOGGER = Logger.getLogger("SpigotSpin");

    public SpigotSpin(@NotNull Plugin plugin) {
        PLUGIN.REFERENCE.setPlugin(plugin);

        registerListeners();
    }


    private void registerListeners() {
        PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(new MenuListener(), PLUGIN.REFERENCE.getPlugin());
    }

    @Getter
    public enum PLUGIN {
        REFERENCE;

        @Setter
        public Plugin plugin;
    }


}
