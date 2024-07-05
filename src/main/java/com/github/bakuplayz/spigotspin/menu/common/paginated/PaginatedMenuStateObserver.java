package com.github.bakuplayz.spigotspin.menu.common.paginated;

import com.github.bakuplayz.spigotspin.menu.common.state.MenuState;
import com.github.bakuplayz.spigotspin.menu.common.state.MenuStateObserver;

public interface PaginatedMenuStateObserver<S extends MenuState> extends MenuStateObserver<S> {

    void onChangePage(int page);

}
