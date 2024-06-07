package com.github.bakuplayz.spigotspin.abstraction.menu.menus.paginated;

import com.github.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuState;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuStateObserver;

public interface PaginatedMenuStateObserver<S extends MenuState> extends MenuStateObserver<S> {

    void onChangePage(int page);

}
