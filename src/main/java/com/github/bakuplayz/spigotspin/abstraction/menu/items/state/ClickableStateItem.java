package com.github.bakuplayz.spigotspin.abstraction.menu.items.state;

import com.github.bakuplayz.spigotspin.abstraction.menu.items.Clickable;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.actions.ItemAction;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuState;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


@Setter
public abstract class ClickableStateItem<S extends MenuState>
        extends StateItem<S> implements Clickable, StateUpdatable<S> {


    private ItemAction action;


    @Override
    public void performAction(@NotNull Player player) {
        if (action == null) {
            throw new RuntimeException("Action cannot be null.");
        }

        action.performAction(this, player);
    }


}
