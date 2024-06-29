package com.github.bakuplayz.spigotspin.menu.items.actions;

import com.github.bakuplayz.spigotspin.menu.items.DraggableItem;
import com.github.bakuplayz.spigotspin.menu.items.Item;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface DraggableAction extends ItemAction {

    @Override
    default void performAction(@NotNull Item item, @NotNull Player player) {
        onDrag((DraggableItem) item, player);
    }


    void onDrag(@NotNull DraggableItem item, @NotNull Player player);

}
