package dev.bakuplayz.spigotspin.abstraction.menu.items.paginated;

import com.cryptomorin.xseries.XMaterial;
import dev.bakuplayz.spigotspin.abstraction.menu.items.state.ClickableStateItem;
import dev.bakuplayz.spigotspin.abstraction.menu.items.utils.ViewState;
import dev.bakuplayz.spigotspin.abstraction.menu.menus.paginated.PaginatedMenuState;
import dev.bakuplayz.spigotspin.abstraction.menu.menus.paginated.PaginationContext;
import org.jetbrains.annotations.NotNull;

public class NextPageItem<S extends PaginatedMenuState> extends ClickableStateItem<S> {

    private final PaginationContext context;


    public NextPageItem(@NotNull PaginationContext context) {
        this.context = context;

        setName("&7Next page");
        setMaterial(XMaterial.ARROW);
        // TODO: handle potential initial view state.
    }


    @Override
    public void update(@NotNull S state, int flag) {
        setViewState(context.hasNextPage() ? ViewState.VISIBLE : ViewState.INVISIBLE);
    }


}
