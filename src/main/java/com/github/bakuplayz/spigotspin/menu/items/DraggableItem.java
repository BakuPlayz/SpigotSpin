package com.github.bakuplayz.spigotspin.menu.items;

import com.github.bakuplayz.spigotspin.menu.items.actions.ItemAction;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Setter
public abstract class DraggableItem extends Item implements Draggable {

    @Nullable
    private ItemAction action;


    @Override
    public final void performAction(@NotNull Player player) {
        if (action == null) {
            throw new IllegalArgumentException("Action cannot be null.");
        }

        action.performAction(this, player);
    }

}
