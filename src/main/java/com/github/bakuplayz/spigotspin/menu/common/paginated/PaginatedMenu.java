package com.github.bakuplayz.spigotspin.menu.common.paginated;

import com.github.bakuplayz.spigotspin.menu.items.Item;
import com.github.bakuplayz.spigotspin.menu.items.actions.ItemAction;
import com.github.bakuplayz.spigotspin.menu.items.paginated.CurrentPageItem;
import com.github.bakuplayz.spigotspin.menu.items.paginated.NextPageItem;
import com.github.bakuplayz.spigotspin.menu.items.paginated.PreviousPageItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PaginatedMenu<S extends PaginatedMenuState, PI> extends PaginationContext {


    void loadPaginatedItems(@NotNull List<Item> batch);


    @Nullable
    default List<PI> getPaginationItems() {
        return Collections.emptyList();
    }


    @Nullable
    default CompletableFuture<List<PI>> getFuturePaginationItems() {
        return null;
    }


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
