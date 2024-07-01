package com.github.bakuplayz.spigotspin.menu.menus.abstracts;

import com.github.bakuplayz.spigotspin.menu.items.actions.ClickableAction;
import com.github.bakuplayz.spigotspin.menu.items.state.ClickableStateItem;
import com.github.bakuplayz.spigotspin.menu.items.state.StateItem;
import com.github.bakuplayz.spigotspin.menu.menus.common.components.StateComponent;
import com.github.bakuplayz.spigotspin.menu.menus.common.state.MenuState;
import com.github.bakuplayz.spigotspin.menu.menus.common.state.MenuStateHandler;
import com.github.bakuplayz.spigotspin.menu.menus.common.state.MenuStateObserver;
import com.github.bakuplayz.spigotspin.menu.menus.common.state.StateMenu;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStateMenu<S extends MenuState, SH extends MenuStateHandler<S, ? extends MenuStateObserver<S>>>
        extends AbstractPlainMenu implements StateMenu<S, SH>, MenuStateObserver<S> {

    private final StateComponent<S> stateComponent;

    @NotNull
    protected SH stateHandler;


    public AbstractStateMenu(@NotNull String title) {
        super(title);
        this.stateHandler = createStateHandler();
        this.stateComponent = new StateComponent<>(this, stateHandler);
    }


    @Override
    public void setItem(int position, @NotNull StateItem<S> item, int flag) {
        validatePosition(position);
        item.setFlags(Collections.singletonList(flag));
        item.injectInitialState(stateComponent.getState());
        item.injectDispatcher(getDispatcher());
        item.setPosition(position);
        items.put(position, item);
    }


    @Override
    public void setItemIf(boolean setIfTrue, int position, @NotNull StateItem<S> item, int flag) {
        if (setIfTrue) setItem(position, item, flag);
    }


    @Override
    public void setItem(int position, @NotNull ClickableStateItem<S> item, @NotNull ClickableAction<ClickableStateItem<S>> action, int flag) {
        this.setItem(position, item, action, Collections.singletonList(flag));
    }


    @Override
    public void setItemIf(boolean setIfTrue, int position, @NotNull ClickableStateItem<S> item, @NotNull ClickableAction<ClickableStateItem<S>> action, int flag) {
        if (setIfTrue) setItem(position, item, action, flag);
    }


    @Override
    public void setItem(int position, @NotNull ClickableStateItem<S> item, @NotNull ClickableAction<ClickableStateItem<S>> action, @NotNull List<Integer> flags) {
        validatePosition(position);
        item.setFlags(flags);
        item.setAction(action);
        item.setPosition(position);
        item.injectDispatcher(getDispatcher());
        item.injectInitialState(stateComponent.getState());
        items.put(position, item);
    }


    @Override
    public void setItemIf(boolean setIfTrue, int position, @NotNull ClickableStateItem<S> item, @NotNull ClickableAction<ClickableStateItem<S>> action, @NotNull List<Integer> flags) {
        if (setIfTrue) setItem(position, item, action, flags);
    }


    @Override
    public final void onStateChanged(@NotNull S state, int flag) {
        stateComponent.handleUpdate(state, flag);
    }


    @Override
    public void onPoppedTo() {
        setItems();
    }

}
