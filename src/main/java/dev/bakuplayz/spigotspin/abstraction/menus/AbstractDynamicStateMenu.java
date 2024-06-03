package dev.bakuplayz.spigotspin.abstraction.menus;

import dev.bakuplayz.spigotspin.abstraction.items.actions.ClickableAction;
import dev.bakuplayz.spigotspin.abstraction.items.state.ClickableStateItem;
import dev.bakuplayz.spigotspin.abstraction.items.state.StateItem;
import dev.bakuplayz.spigotspin.abstraction.items.state.StateUpdatable;
import dev.bakuplayz.spigotspin.abstraction.menus.state.DynamicStateMenu;
import dev.bakuplayz.spigotspin.abstraction.menus.state.MenuState;
import dev.bakuplayz.spigotspin.abstraction.menus.state.MenuStateObserver;
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
        item.injectDispatcher(dispatcher);
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
        item.injectDispatcher(dispatcher);
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
