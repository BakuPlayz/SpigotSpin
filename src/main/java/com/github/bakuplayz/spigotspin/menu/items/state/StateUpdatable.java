package com.github.bakuplayz.spigotspin.menu.items.state;

import com.github.bakuplayz.spigotspin.menu.menus.common.state.MenuState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface StateUpdatable<S extends MenuState> {

    @NotNull
    List<Integer> getFlags();


    void setFlags(@NotNull List<Integer> flags);


    void update(@NotNull S state, int flag);


    void onUpdate(@NotNull S state, int flag);

}
