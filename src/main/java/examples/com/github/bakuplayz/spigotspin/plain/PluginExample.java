package examples.com.github.bakuplayz.spigotspin.plain;

import com.github.bakuplayz.spigotspin.SpigotSpin;
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
