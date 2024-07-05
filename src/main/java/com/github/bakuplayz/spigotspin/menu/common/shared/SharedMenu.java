package com.github.bakuplayz.spigotspin.menu.common.shared;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface SharedMenu {


    void join(@NotNull Player player, @NotNull String identifier);


    void leave(@NotNull Player player);

}
