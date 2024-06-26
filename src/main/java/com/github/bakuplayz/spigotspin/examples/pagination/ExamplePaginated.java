package com.github.bakuplayz.spigotspin.examples.pagination;

import com.github.bakuplayz.spigotspin.menu.items.Item;
import com.github.bakuplayz.spigotspin.menu.items.actions.ItemAction;
import com.github.bakuplayz.spigotspin.menu.menus.SizeType;
import com.github.bakuplayz.spigotspin.menu.menus.abstracts.AbstractPaginatedMenu;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public final class ExamplePaginated extends AbstractPaginatedMenu<ExampleState, ExampleStateHandler, String> {


    private final List<String> items = Arrays.asList("Hello", "World!");


    public ExamplePaginated() {
        super("Paginated example");
        setPaginationItems(items);
    }


    @NotNull
    @Override
    public ItemAction getPaginatedItemAction(@NotNull String paginatedItem, int position) {
        return (item, player) -> player.sendMessage("You clicked at an item :O");
    }


    @NotNull
    @Override
    public Item loadPaginatedItem(@NotNull String paginatedItem, int position) {
        return new ExampleItem(paginatedItem);
    }


    @Override
    public SizeType getSizeType() {
        return SizeType.DYNAMIC;
    }


    @NotNull
    @Override
    public ExampleStateHandler createStateHandler() {
        return new ExampleStateHandler(this);
    }

}
