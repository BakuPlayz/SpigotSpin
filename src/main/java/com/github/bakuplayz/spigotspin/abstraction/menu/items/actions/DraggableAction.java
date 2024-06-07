package com.github.bakuplayz.spigotspin.abstraction.menu.items.actions;

import com.github.bakuplayz.spigotspin.abstraction.menu.items.DraggableItem;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface DraggableAction {

    void onDrag(@NotNull DraggableItem item, @NotNull Player player);

}
