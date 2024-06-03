package dev.bakuplayz.spigotspin.examples.plain;

import dev.bakuplayz.spigotspin.SpigotSpin;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginExample extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register SpigotSpin
        new SpigotSpin(this);
    }


    @Override
    public void onDisable() {

    }

}
