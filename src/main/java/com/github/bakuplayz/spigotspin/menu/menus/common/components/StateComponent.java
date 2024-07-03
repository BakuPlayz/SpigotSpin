package com.github.bakuplayz.spigotspin.menu.menus.common.components;

import com.github.bakuplayz.spigotspin.menu.items.state.StateUpdatable;
import com.github.bakuplayz.spigotspin.menu.menus.Menu;
import com.github.bakuplayz.spigotspin.menu.menus.abstracts.AbstractStateMenu;
import com.github.bakuplayz.spigotspin.menu.menus.common.state.MenuState;
import org.jetbrains.annotations.NotNull;

public final class StateComponent<S extends MenuState> {


    private final Menu menu;


    public StateComponent(@NotNull AbstractStateMenu<S, ?> menu) {
        this.menu = menu;
    }


    public void handleUpdate(@NotNull S state, int flag) {
        menu.getItems().forEach((position, item) -> {
            /* We don't allow changes to items being of class
             * non-clickable state item, as they don't rely on
             * or update a state by being interacted with. */
            if (!(item instanceof StateUpdatable)) return;

            /*
             * We have an updatable item, but we shall never
             * update more than once. So if the updatable has
             * a flag that matches the state update, then we
             * update all depending on items, iff they don't depend
             * on the flags then just ignore it.
             */
            @SuppressWarnings("unchecked")
            StateUpdatable<S> updatable = ((StateUpdatable<S>) item);
            for (int updatableFlag : updatable.getFlags()) {
                if (updatableFlag != flag) continue;
                updatable.onUpdate(state, updatableFlag);
            }
        });
    }

}
