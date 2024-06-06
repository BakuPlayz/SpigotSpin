package dev.bakuplayz.spigotspin.abstraction.menu.items;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface Clickable {

    void performAction(@NotNull Player player);

}
