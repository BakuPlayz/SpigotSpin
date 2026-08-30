package com.github.bakuplayz.spigotspin.menu.items.paginated;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.menu.common.paginated.PaginatedMenuState;
import com.github.bakuplayz.spigotspin.menu.common.paginated.PaginationContext;
import com.github.bakuplayz.spigotspin.menu.items.common.ActionState;
import com.github.bakuplayz.spigotspin.menu.items.common.ViewState;
import com.github.bakuplayz.spigotspin.menu.items.state.ClickableStateItem;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class PreviousPageItem<S extends PaginatedMenuState> extends ClickableStateItem<S> {

    protected final PaginationContext context;


    @Override
    public CompletableFuture<Void> create() {
        return createSync(() -> {
            setName("&7Previous page");
            setMaterial(XMaterial.ARROW);
            setViewState(ViewState.INVISIBLE);
        });
    }


    @Override
    public void update(@NotNull S state, int flag) {
        setViewState(state.getPage() <= 0 ? ViewState.INVISIBLE : ViewState.VISIBLE);
        setActionState(state.getPage() <= 0 ? ActionState.DISABLED : ActionState.ENABLED);
    }


}
