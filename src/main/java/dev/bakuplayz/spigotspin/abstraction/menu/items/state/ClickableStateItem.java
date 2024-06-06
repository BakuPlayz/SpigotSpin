package dev.bakuplayz.spigotspin.abstraction.menu.items.state;

import dev.bakuplayz.spigotspin.abstraction.menu.items.Clickable;
import dev.bakuplayz.spigotspin.abstraction.menu.items.actions.ClickableAction;
import dev.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuState;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


@Setter
public abstract class ClickableStateItem<S extends MenuState>
        extends StateItem<S> implements Clickable, StateUpdatable<S> {


    private ClickableAction<ClickableStateItem<S>> action;


    @Override
    public void performAction(@NotNull Player player) {
        if (action == null) {
            throw new RuntimeException("Action cannot be null.");
        }

        action.onClick(this, player);
    }


}
