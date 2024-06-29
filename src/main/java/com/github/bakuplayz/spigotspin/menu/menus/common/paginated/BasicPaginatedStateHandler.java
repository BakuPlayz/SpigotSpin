package com.github.bakuplayz.spigotspin.menu.menus.common.paginated;

import org.jetbrains.annotations.NotNull;

public final class BasicPaginatedStateHandler extends PaginatedMenuStateHandler<BasicPaginatedMenuState> {

    public BasicPaginatedStateHandler(@NotNull PaginatedMenuStateObserver<BasicPaginatedMenuState> observer) {
        super(observer, new BasicPaginatedMenuState());
    }

}
