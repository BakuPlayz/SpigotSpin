package com.github.bakuplayz.spigotspin.abstraction.menu.items.actions;

import com.github.bakuplayz.spigotspin.abstraction.menu.items.Item;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ItemAction {

     void performAction(@NotNull Item item, @NotNull Player player);

}
