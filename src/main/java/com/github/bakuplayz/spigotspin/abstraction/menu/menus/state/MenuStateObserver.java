package com.github.bakuplayz.spigotspin.abstraction.menu.menus.state;

import org.jetbrains.annotations.NotNull;

public interface MenuStateObserver<S extends MenuState> {

    void onStateChanged(@NotNull S state, int flag);

}
