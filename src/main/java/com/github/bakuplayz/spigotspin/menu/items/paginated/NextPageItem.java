package com.github.bakuplayz.spigotspin.menu.items.paginated;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.menu.items.state.ClickableStateItem;
import com.github.bakuplayz.spigotspin.menu.items.utils.ViewState;
import com.github.bakuplayz.spigotspin.menu.common.paginated.PaginatedMenuState;
import com.github.bakuplayz.spigotspin.menu.common.paginated.PaginationContext;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public class NextPageItem<S extends PaginatedMenuState> extends ClickableStateItem<S> {

    protected final PaginationContext context;


    @Override
    public void create() {
        setName("&7Next page");
        setMaterial(XMaterial.ARROW);
    }


    @Override
    public void update(@NotNull S state, int flag) {
        setViewState(context.hasNextPage() ? ViewState.VISIBLE : ViewState.INVISIBLE);
    }


}
