package com.github.bakuplayz.spigotspin.abstraction.menu.menus;

import com.github.bakuplayz.spigotspin.abstraction.menu.items.actions.ClickableAction;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.state.ClickableStateItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.state.StateItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.state.StateUpdatable;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.state.DynamicStateMenu;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuState;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuStateObserver;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Collections;
import java.util.List;


@Getter
public abstract class AbstractDynamicStateMenu<S extends MenuState>
        extends AbstractDynamicPlainMenu implements DynamicStateMenu<S>, MenuStateObserver<S> {

    protected AbstractDynamicStateMenu(@NotNull String title) {
        super(title);
    }


    @Override
    public final void setItem(
            @Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position,
            @NotNull StateItem<S> item,
            int flag
    ) {
        item.setFlags(Collections.singletonList(flag));
        item.injectDispatcher(getDispatcher());
        item.setPosition(position);
        items.put(position, item);
    }


    @Override
    public final void setItem(
            @Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position,
            @NotNull ClickableStateItem<S> item,
            @NotNull ClickableAction<ClickableStateItem<S>> action,
            int flag
    ) {
        this.setItem(position, item, action, Collections.singletonList(flag));
    }


    @Override
    public final void setItem(
            @Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position,
            @NotNull ClickableStateItem<S> item,
            @NotNull ClickableAction<ClickableStateItem<S>> action,
            @NotNull List<Integer> flags
    ) {
        item.setFlags(flags);
        item.setAction(action);
        item.setPosition(position);
        item.injectDispatcher(getDispatcher());
        items.put(position, item);
    }


    @Override
    public final void onStateChanged(@NotNull S state, int flag) {
        items.forEach((position, item) -> {
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
