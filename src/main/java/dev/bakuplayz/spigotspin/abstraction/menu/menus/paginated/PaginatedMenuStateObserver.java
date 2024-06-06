package dev.bakuplayz.spigotspin.abstraction.menu.menus.paginated;

import dev.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuState;
import dev.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuStateObserver;

public interface PaginatedMenuStateObserver<S extends MenuState> extends MenuStateObserver<S> {

    void onChangePage(int page);

}
