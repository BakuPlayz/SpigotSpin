package com.github.bakuplayz.spigotspin.examples.pagination;

import com.github.bakuplayz.spigotspin.abstraction.menu.items.Item;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.actions.DraggableAction;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.actions.ItemAction;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.AbstractDynamicPaginatedMenu;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public final class ExamplePaginated extends AbstractDynamicPaginatedMenu<ExampleState, ExampleStateHandler> {


    private final List<String> items = Arrays.asList("Hello", "World!");


    public ExamplePaginated() {
        super("Paginated example");
        setPaginationItems(items);
    }


    @NotNull
    @Override
    public ItemAction<Item> getPaginatedItemAction(int position) {
        return (item, player) -> {
            player.sendMessage("You clicked at an item :O");
        };
    }


    @NotNull
    @Override
    public Item loadPaginatedItem(int itemPosition) {
        return new ExampleItem(items.get(itemPosition));
    }

}
