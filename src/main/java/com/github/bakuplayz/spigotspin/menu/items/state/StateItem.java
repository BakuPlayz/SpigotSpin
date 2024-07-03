package com.github.bakuplayz.spigotspin.menu.items.state;

import com.github.bakuplayz.spigotspin.menu.dispatchers.InventoryDispatcher;
import com.github.bakuplayz.spigotspin.menu.items.Item;
import com.github.bakuplayz.spigotspin.menu.menus.common.state.MenuState;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class StateItem<S extends MenuState> extends Item implements StateUpdatable<S> {


    @Setter
    @Getter
    @NotNull
    private List<Integer> flags;

    @Getter
    private S initialState;

    private InventoryDispatcher dispatcher;


    public final void injectDispatcher(@NotNull InventoryDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }


    public final void injectInitialState(@NotNull S initialState) {
        this.initialState = initialState;
    }


    @Override
    public void onUpdate(@NotNull S state, int flag) {
        update(state, flag);
        dispatcher.updateItem(this);
    }

}
