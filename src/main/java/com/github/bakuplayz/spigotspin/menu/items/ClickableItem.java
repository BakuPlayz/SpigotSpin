package com.github.bakuplayz.spigotspin.menu.items;

import com.github.bakuplayz.spigotspin.menu.items.actions.ItemAction;
import com.github.bakuplayz.spigotspin.menu.items.common.ActionState;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Setter
public abstract class ClickableItem extends Item implements ItemActionable, Clickable {

    @Nullable
    private ItemAction action;

    @Setter
    private ActionState actionState = ActionState.ENABLED;


    @Override
    @Nullable
    public ItemAction getAction() {
        return action;
    }


    @Override
    public final void performAction(@NotNull Player player) {
        ItemAction action = getAction();
        if (action == null) {
            throw new IllegalArgumentException("Action cannot be null.");
        }

        if (actionState == ActionState.ENABLED) {
            action.performAction(this, player);
        }
    }

}

