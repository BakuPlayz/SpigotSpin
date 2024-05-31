package dev.bakuplayz.spigotspin.abstraction.items.state;

import dev.bakuplayz.spigotspin.abstraction.items.Clickable;
import dev.bakuplayz.spigotspin.abstraction.items.actions.ClickableAction;
import dev.bakuplayz.spigotspin.abstraction.menus.state.MenuState;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;


@Setter
public abstract class ClickableStateItem<S extends MenuState>
        extends StateItem<S> implements Clickable, StateUpdatable<S> {


    @Nullable
    private ClickableAction action;


    @Override
    public void performAction() {
        if (action == null) {
            throw new RuntimeException("Action cannot be null.");
        }

        action.onClick(this);
    }


}
