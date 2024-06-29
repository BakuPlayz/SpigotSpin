package com.github.bakuplayz.spigotspin.menu.menus.common.paginated;

import com.github.bakuplayz.spigotspin.menu.items.Item;
import com.github.bakuplayz.spigotspin.menu.items.actions.ItemAction;
import com.github.bakuplayz.spigotspin.menu.items.paginated.CurrentPageItem;
import com.github.bakuplayz.spigotspin.menu.items.paginated.NextPageItem;
import com.github.bakuplayz.spigotspin.menu.items.paginated.PreviousPageItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface PaginatedMenu<S extends PaginatedMenuState, PI> extends PaginationContext {


    void loadPaginatedItems(@NotNull List<Item> batch);


    @Nullable
    List<PI> getPaginationItems();


    void setPaginationItems(@NotNull List<PI> items);


    ItemAction getPaginatedItemAction(@NotNull PI paginatedItem, int position);


    @NotNull
    Item loadPaginatedItem(@NotNull PI paginatedItem, int position);


    @NotNull
    PreviousPageItem<S> getPreviousItem();


    @NotNull
    default PreviousPageItem<S> createPreviousItem() {
        return new PreviousPageItem<>(this);
    }


    @NotNull
    CurrentPageItem<S> getCurrentItem();


    @NotNull
    default CurrentPageItem<S> createCurrentItem() {
        return new CurrentPageItem<>(this);
    }


    @NotNull
    NextPageItem<S> getNextItem();


    @NotNull
    default NextPageItem<S> createNextItem() {
        return new NextPageItem<>(this);
    }

}
