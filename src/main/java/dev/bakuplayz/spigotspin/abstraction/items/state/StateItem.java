package dev.bakuplayz.spigotspin.abstraction.items.state;

import dev.bakuplayz.spigotspin.abstraction.dispatchers.DynamicMenuDispatcher;
import dev.bakuplayz.spigotspin.abstraction.items.Item;
import dev.bakuplayz.spigotspin.abstraction.menus.state.MenuState;
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
