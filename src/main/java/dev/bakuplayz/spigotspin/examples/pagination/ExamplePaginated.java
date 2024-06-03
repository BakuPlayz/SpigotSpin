package dev.bakuplayz.spigotspin.examples.pagination;

import dev.bakuplayz.spigotspin.abstraction.items.Item;
import dev.bakuplayz.spigotspin.abstraction.menus.AbstractDynamicPaginatedMenu;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public final class ExamplePaginated extends AbstractDynamicPaginatedMenu<ExampleState, ExampleStateHandler> {


    private final List<String> items = Arrays.asList("Hello", "World!");


    public ExamplePaginated() {
        super("Paginated example");
    }


    @Override
    public int getItemsAmount() {
        return items.size();
    }


    @NotNull
    @Override
    public Item loadItem(int itemPosition, int inventoryPosition, int page, int displayPage) {
        return new ExampleItem(items.get(itemPosition * displayPage));
    }

}
