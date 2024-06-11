package com.github.bakuplayz.spigotspin;

import com.github.bakuplayz.spigotspin.abstraction.menu.listeners.MenuListener;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

// TODO: Make this reference creation,
//  and registering of listeners become
//  annotation automated.
public final class SpigotSpin {

    public static final Logger LOGGER = Logger.getLogger("SpigotSpin");


    public SpigotSpin(@NotNull Plugin plugin) {
        PLUGIN.REF.setPlugin(plugin);

        registerListeners();
    }


    private void registerListeners() {
        PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(new MenuListener(), PLUGIN.REF.getPlugin());
    }

    /**
    * A enum (static-like) reference to the inheriting
    * plugin in-order to register i.e. events. This is
    * done in this manner, due to the multi-threading nature
    * that some extending plugins require.
    * */
    @Getter
    public enum PLUGIN {

        /**
         * For the curious ones, these can be whatever,
         * it's just used as an shorter way to access
         * the variables under the enum.
         */
        REF;

        /**
         * Plugin reference to the extending plugin.
         */
        @Setter
        public Plugin plugin;

    }


}
