package dev.bakuplayz.spigotspin.abstraction.items.state;

import dev.bakuplayz.spigotspin.abstraction.menus.state.MenuState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface StateUpdatable<S extends MenuState> {

    @NotNull
    List<Integer> getFlags();


    void setFlags(@NotNull List<Integer> flags);


    void update(@NotNull S state, int flag);


    void onUpdate(@NotNull S state, int flag);

}
