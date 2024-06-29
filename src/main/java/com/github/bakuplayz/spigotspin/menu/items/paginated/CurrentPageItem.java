package com.github.bakuplayz.spigotspin.menu.items.paginated;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.menu.items.state.StateItem;
import com.github.bakuplayz.spigotspin.menu.menus.common.paginated.PaginatedMenuState;
import com.github.bakuplayz.spigotspin.menu.menus.common.paginated.PaginationContext;
import org.jetbrains.annotations.NotNull;

public class CurrentPageItem<S extends PaginatedMenuState> extends StateItem<S> {

    protected final PaginationContext context;


    public CurrentPageItem(@NotNull PaginationContext context) {
        this.context = context;

        setName("&7Page: &e1");
        setMaterial(XMaterial.BOOK);
    }


    @Override
    public void update(@NotNull S state, int flag) {
        setName(String.format("&7Page: &e%s", state.getDisplayPage()));
    }

}
