package com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.SpigotSpin;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.state.ClickableStateItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.utils.ViewState;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.paginated.PaginatedMenuState;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.paginated.PaginationContext;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class NextPageItem<S extends PaginatedMenuState> extends ClickableStateItem<S> {

    private final PaginationContext context;


    public NextPageItem(@NotNull PaginationContext context) {
        this.context = context;

        setName("&7Next page");
        setMaterial(XMaterial.ARROW);

        // Has to run after a small-delay since the items has not been sat yet,
        // inside the menu making it unable to know if it has a next page.
        Bukkit.getScheduler().runTaskLater(SpigotSpin.PLUGIN.REFERENCE.getPlugin(), () -> {
            setViewState(context.hasNextPage() ? ViewState.VISIBLE : ViewState.INVISIBLE);
        }, 1);
    }


    @Override
    public void update(@NotNull S state, int flag) {
        setViewState(context.hasNextPage() ? ViewState.VISIBLE : ViewState.INVISIBLE);
    }


}
