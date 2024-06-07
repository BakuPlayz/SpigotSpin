package com.github.bakuplayz.spigotspin.abstraction.menu.items;

import com.github.bakuplayz.spigotspin.abstraction.menu.items.actions.ClickableAction;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Setter
public abstract class ClickableItem extends Item implements Clickable {

    @Nullable
    private ClickableAction<ClickableItem> action;


    @Override
    public void performAction(@NotNull Player player) {
        if (action == null) {
            throw new IllegalArgumentException("Action cannot be null.");
        }

        action.onClick(this, player);
    }

}

