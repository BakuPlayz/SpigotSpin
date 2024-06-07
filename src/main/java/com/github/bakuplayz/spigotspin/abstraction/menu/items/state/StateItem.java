package com.github.bakuplayz.spigotspin.abstraction.menu.items.state;

import com.github.bakuplayz.spigotspin.abstraction.menu.dispatchers.DynamicMenuDispatcher;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.Item;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuState;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class StateItem<S extends MenuState> extends Item implements StateUpdatable<S> {


    @Setter
    @Getter
    @NotNull
    private List<Integer> flags;

    private DynamicMenuDispatcher dispatcher;


    public final void injectDispatcher(@NotNull DynamicMenuDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }


    @Override
    public void onUpdate(@NotNull S state, int flag) {
        update(state, flag);
        dispatcher.updateItem(this);
    }

}
