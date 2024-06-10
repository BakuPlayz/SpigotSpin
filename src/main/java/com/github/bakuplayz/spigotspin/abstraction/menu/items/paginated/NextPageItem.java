package com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.state.ClickableStateItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.utils.ViewState;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.paginated.PaginatedMenuState;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.paginated.PaginatedMenuStateFlag;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.paginated.PaginationContext;
import org.jetbrains.annotations.NotNull;

public class NextPageItem<S extends PaginatedMenuState> extends ClickableStateItem<S> {

    private final PaginationContext context;


    public NextPageItem(@NotNull PaginationContext context) {
        this.context = context;

        setName("&7Next page");
        setMaterial(XMaterial.ARROW);
    }


    @Override
    public void update(@NotNull S state, int flag) {
        setViewState(context.hasNextPage() ? ViewState.VISIBLE : ViewState.INVISIBLE);
    }

}
