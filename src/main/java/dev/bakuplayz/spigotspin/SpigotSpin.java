package dev.bakuplayz.spigotspin;

import dev.bakuplayz.spigotspin.abstraction.listeners.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public final class SpigotSpin {

    public static final Logger LOGGER = Logger.getLogger("SpigotSpin");

    private final Plugin plugin;


    public SpigotSpin(@NotNull Plugin plugin) {
        this.plugin = plugin;

        registerListeners();
    }


    private void registerListeners() {
        PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(new MenuListener(), plugin);
    }


}
