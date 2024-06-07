package com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.state.ClickableStateItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.utils.ViewState;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.paginated.PaginatedMenuState;
import org.jetbrains.annotations.NotNull;

public class PreviousPageItem<S extends PaginatedMenuState> extends ClickableStateItem<S> {

    public PreviousPageItem() {
        setName("&7Previous page");
        setMaterial(XMaterial.ARROW);
        setViewState(ViewState.INVISIBLE);
    }


    @Override
    public void update(@NotNull S state, int flag) {
        setViewState(state.getPage() <= 0 ? ViewState.INVISIBLE : ViewState.VISIBLE);
    }

}
