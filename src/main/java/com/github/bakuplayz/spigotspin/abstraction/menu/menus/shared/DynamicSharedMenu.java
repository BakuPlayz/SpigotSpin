package com.github.bakuplayz.spigotspin.abstraction.menu.menus.shared;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface DynamicSharedMenu {


    void join(@NotNull Player player, @NotNull String identifier);


    void leave(@NotNull Player player);
    
}
