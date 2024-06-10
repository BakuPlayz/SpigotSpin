package com.github.bakuplayz.spigotspin.abstraction.menu.menus.paginated;

import com.github.bakuplayz.spigotspin.abstraction.menu.items.Item;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.actions.ItemAction;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated.CurrentPageItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated.NextPageItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated.PreviousPageItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface DynamicPaginatedMenu<S extends PaginatedMenuState, PI> extends PaginationContext {


    void loadPaginatedItems(@NotNull List<Item> batch);


    void setPaginationItems(@NotNull List<PI> items);


    @Nullable
    List<PI> getPaginationItems();


    ItemAction getPaginatedItemAction(@NotNull PI paginatedItem);


    @NotNull
    Item loadPaginatedItem(@NotNull PI paginatedItem);


    @NotNull
    PreviousPageItem<S> getPreviousItem();


    @NotNull
    default PreviousPageItem<S> createPreviousItem() {
        return new PreviousPageItem<>();
    }


    @NotNull
    CurrentPageItem<S> getCurrentItem();


    @NotNull
    default CurrentPageItem<S> createCurrentItem() {
        return new CurrentPageItem<>();
    }


    @NotNull
    NextPageItem<S> getNextItem();


    @NotNull
    default NextPageItem<S> createNextItem() {
        return new NextPageItem<>(this);
    }

}
