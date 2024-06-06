package dev.bakuplayz.spigotspin.abstraction.menu.items.paginated;

import com.cryptomorin.xseries.XMaterial;
import dev.bakuplayz.spigotspin.abstraction.menu.items.state.StateItem;
import dev.bakuplayz.spigotspin.abstraction.menu.menus.paginated.PaginatedMenuState;
import org.jetbrains.annotations.NotNull;

public class CurrentPageItem<S extends PaginatedMenuState> extends StateItem<S> {

    public CurrentPageItem() {
        setName("&7Page: &e1");
        setMaterial(XMaterial.BOOK);
    }


    @Override
    public void update(@NotNull S state, int flag) {
        setName(String.format("&7Page: &e%s", state.getDisplayPage()));
    }

}
