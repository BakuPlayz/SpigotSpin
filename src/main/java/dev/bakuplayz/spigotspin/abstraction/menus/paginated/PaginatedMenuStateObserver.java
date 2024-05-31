package dev.bakuplayz.spigotspin.abstraction.menus.paginated;

import dev.bakuplayz.spigotspin.abstraction.menus.state.MenuState;
import dev.bakuplayz.spigotspin.abstraction.menus.state.MenuStateObserver;

public interface PaginatedMenuStateObserver<S extends MenuState> extends MenuStateObserver<S> {

    void onChangePage(int page);

}
