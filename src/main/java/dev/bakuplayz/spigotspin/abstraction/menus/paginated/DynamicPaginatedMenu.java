package dev.bakuplayz.spigotspin.abstraction.menus.paginated;

import dev.bakuplayz.spigotspin.abstraction.items.Item;
import dev.bakuplayz.spigotspin.abstraction.items.paginated.CurrentPageItem;
import dev.bakuplayz.spigotspin.abstraction.items.paginated.NextPageItem;
import dev.bakuplayz.spigotspin.abstraction.items.paginated.PreviousPageItem;
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
