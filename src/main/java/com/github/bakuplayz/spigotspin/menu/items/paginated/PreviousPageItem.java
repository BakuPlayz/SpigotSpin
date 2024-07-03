package com.github.bakuplayz.spigotspin.menu.items.paginated;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.menu.items.state.ClickableStateItem;
import com.github.bakuplayz.spigotspin.menu.items.utils.ViewState;
import com.github.bakuplayz.spigotspin.menu.menus.common.paginated.PaginatedMenuState;
import com.github.bakuplayz.spigotspin.menu.menus.common.paginated.PaginationContext;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public class PreviousPageItem<S extends PaginatedMenuState> extends ClickableStateItem<S> {

    protected final PaginationContext context;


    @Override
    public void create() {
        setName("&7Previous page");
        setMaterial(XMaterial.ARROW);
        setViewState(ViewState.INVISIBLE);
    }


    @Override
    public void update(@NotNull S state, int flag) {
        setViewState(state.getPage() <= 0 ? ViewState.INVISIBLE : ViewState.VISIBLE);
    }


}
