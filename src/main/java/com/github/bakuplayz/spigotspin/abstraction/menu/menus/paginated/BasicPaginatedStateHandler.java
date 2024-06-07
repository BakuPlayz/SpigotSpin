package com.github.bakuplayz.spigotspin.abstraction.menu.menus.paginated;

import org.jetbrains.annotations.NotNull;

public final class BasicPaginatedStateHandler extends PaginatedMenuStateHandler<BasicPaginatedMenuState> {

    public BasicPaginatedStateHandler(@NotNull PaginatedMenuStateObserver<BasicPaginatedMenuState> observer) {
        super(observer, new BasicPaginatedMenuState());
    }

}
