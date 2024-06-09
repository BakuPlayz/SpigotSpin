package com.github.bakuplayz.spigotspin.abstraction.menu.menus.paginated;

import com.github.bakuplayz.spigotspin.abstraction.menu.items.Item;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.actions.ItemAction;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated.CurrentPageItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated.NextPageItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated.PreviousPageItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface DynamicPaginatedMenu<PI> extends PaginationContext {


    void loadPaginatedItems(@NotNull List<Item> batch);


    void setPaginationItems(@NotNull List<PI> items);


    @Nullable
    List<PI> getPaginationItems();


    ItemAction getPaginatedItemAction(@NotNull PI paginatedItem);


    @NotNull
    Item loadPaginatedItem(@NotNull PI paginatedItem);


    @NotNull
    PreviousPageItem<PaginatedMenuState> getPreviousItem();


    @NotNull
    CurrentPageItem<PaginatedMenuState> getCurrentItem();


    @NotNull
    NextPageItem<PaginatedMenuState> getNextItem();

}
