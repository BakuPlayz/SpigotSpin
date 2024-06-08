package com.github.bakuplayz.spigotspin.abstraction.menu.menus.paginated;

import com.github.bakuplayz.spigotspin.abstraction.menu.items.Item;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.actions.ItemAction;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated.CurrentPageItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated.NextPageItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated.PreviousPageItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface DynamicPaginatedMenu extends PaginationContext {


    void loadPaginatedItems(@NotNull List<Item> batch);

    <I> void setPaginationItems(@NotNull List<I> items);

    @Nullable
    <I> List<I> getPaginationItems();

    ItemAction<Item> getPaginatedItemAction(int position);


    @NotNull
    Item loadPaginatedItem(int position);


    @NotNull
    PreviousPageItem<PaginatedMenuState> getPreviousItem();


    @NotNull
    CurrentPageItem<PaginatedMenuState> getCurrentItem();


    @NotNull
    NextPageItem<PaginatedMenuState> getNextItem();

}
