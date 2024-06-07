package com.github.bakuplayz.spigotspin.abstraction.menu.menus.paginated;

import com.github.bakuplayz.spigotspin.abstraction.menu.items.Item;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated.CurrentPageItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated.NextPageItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated.PreviousPageItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface DynamicPaginatedMenu extends PaginationContext {


    int getItemsAmount();


    void loadItems(@NotNull List<Item> batch);


    @NotNull
    Item loadItem(int itemPosition, int inventoryPosition, int page, int displayPage);


    @NotNull
    PreviousPageItem<PaginatedMenuState> getPreviousItem();


    @NotNull
    CurrentPageItem<PaginatedMenuState> getCurrentItem();


    @NotNull
    NextPageItem<PaginatedMenuState> getNextItem();

}
