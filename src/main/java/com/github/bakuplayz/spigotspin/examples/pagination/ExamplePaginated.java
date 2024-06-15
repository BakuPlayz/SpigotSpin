package com.github.bakuplayz.spigotspin.examples.pagination;

import com.github.bakuplayz.spigotspin.abstraction.menu.items.Item;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.actions.ItemAction;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.AbstractDynamicPaginatedMenu;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public final class ExamplePaginated extends AbstractDynamicPaginatedMenu<ExampleState, ExampleStateHandler, String> {


    private final List<String> items = Arrays.asList("Hello", "World!");


    public ExamplePaginated() {
        super("Paginated example");
        setPaginationItems(items);
    }


    @NotNull
    @Override
    public ItemAction getPaginatedItemAction(@NotNull String paginatedItem) {
        return (item, player) -> player.sendMessage("You clicked at an item :O");
    }


    @NotNull
    @Override
    public Item loadPaginatedItem(@NotNull String paginatedItem, int position) {
        return new ExampleItem(paginatedItem);
    }

}
