package dev.bakuplayz.spigotspin.abstraction.items.paginated;

import com.cryptomorin.xseries.XMaterial;
import dev.bakuplayz.spigotspin.abstraction.items.state.ClickableStateItem;
import dev.bakuplayz.spigotspin.abstraction.items.utils.ViewState;
import dev.bakuplayz.spigotspin.abstraction.menus.paginated.PaginatedMenuState;
import dev.bakuplayz.spigotspin.abstraction.menus.paginated.PaginationContext;
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
