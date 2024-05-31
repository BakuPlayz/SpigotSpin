package dev.bakuplayz.spigotspin.abstraction.items.paginated;

import com.cryptomorin.xseries.XMaterial;
import dev.bakuplayz.spigotspin.abstraction.items.state.ClickableStateItem;
import dev.bakuplayz.spigotspin.abstraction.items.utils.ViewState;
import dev.bakuplayz.spigotspin.abstraction.menus.paginated.PaginatedMenuState;
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
