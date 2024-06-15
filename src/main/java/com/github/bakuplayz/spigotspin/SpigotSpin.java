package com.github.bakuplayz.spigotspin;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.abstraction.menu.MenuManager;
import com.github.bakuplayz.spigotspin.abstraction.menu.dispatchers.HistoryDispatcher;
import com.github.bakuplayz.spigotspin.abstraction.menu.listeners.MenuListener;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

// TODO: Make this reference creation,
//  and registering of listeners become
//  annotation automated.
public final class SpigotSpin {

    public static final Logger LOGGER = Logger.getLogger("SpigotSpin");


    public SpigotSpin(@NotNull org.bukkit.plugin.Plugin plugin) {
        Manager.REF.setHistory(new HistoryDispatcher());
        Manager.REF.setMenuManager(new MenuManager());
        Plugin.REF.setPlugin(plugin);

        if (XMaterial.supports(XMaterial.getVersion())) {
            LOGGER.severe("You're Minecraft version is not supported with SpigotSpin.");
        }

        registerListeners();
    }


    private void registerListeners() {
        PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(new MenuListener(Manager.REF.getMenuManager()), Plugin.REF.getPlugin());
    }


    /**
     * An enum (static-like) reference to the inheriting
     * plugin in-order to register i.e. events. This is
     * done in this manner, due to the multi-threading nature
     * that some extending plugins require.
     */
    @Getter
    public enum Plugin {

        /**
         * For the curious ones, these can be whatever,
         * it's just used as an shorter way to access
         * the variables under the enum.
         */
        REF;

        /**
         * Plugin reference to the extending plugin.
         */
        @Setter(AccessLevel.PROTECTED)
        private org.bukkit.plugin.Plugin plugin;

    }

    @Getter
    public enum Manager {

        REF;

        @Setter(AccessLevel.PROTECTED)
        private MenuManager menuManager;

        @Setter(AccessLevel.PROTECTED)
        private HistoryDispatcher history;

    }


}
