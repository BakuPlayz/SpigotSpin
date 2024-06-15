package com.github.bakuplayz.spigotspin.abstraction.menu.menus;

import com.github.bakuplayz.spigotspin.abstraction.menu.items.ClickableItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.DraggableItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.Item;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.actions.ItemAction;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated.CurrentPageItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated.NextPageItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.paginated.PreviousPageItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.state.StateItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.handlers.OpenInventoryHandler;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.paginated.*;
import com.github.bakuplayz.spigotspin.abstraction.menu.utils.Collection;
import com.github.bakuplayz.spigotspin.abstraction.menu.utils.TypeUtils;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public abstract class AbstractDynamicPaginatedMenu<S extends PaginatedMenuState, SH extends PaginatedMenuStateHandler<S>, PI>
        extends AbstractDynamicStateMenu<S> implements DynamicPaginatedMenu<S, PI>, PaginatedMenuStateObserver<S> {

    public static final int PAGE_ITEM_INDEX_MIN = 0;

    public static final int PAGE_ITEM_INDEX_MAX = 45;

    private final NextPageItem<S> nextItem;

    private final CurrentPageItem<S> currentItem;

    private final PreviousPageItem<S> previousItem;

    @Setter
    @NotNull
    protected SH stateHandler;

    @Getter
    @Setter
    private List<PI> paginationItems;


    public AbstractDynamicPaginatedMenu(@NotNull String title) {
        super(title);

        this.nextItem = createNextItem();
        this.currentItem = createCurrentItem();
        this.previousItem = createPreviousItem();
        this.paginationItems = Collections.emptyList();
    }


    @Override
    public void open(@NotNull Player player) {
        open(player, new OpenMenuHandler());
    }


    @Override
    public void setItems() {
        setItem(48, TypeUtils.infer(getPreviousItem()), (ignored, player) -> stateHandler.decreasePage(), PaginatedMenuStateFlag.PAGE);
        setItem(49, TypeUtils.infer(getCurrentItem()), PaginatedMenuStateFlag.PAGE);
        setItem(50, TypeUtils.infer(getNextItem()), (ignored, player) -> stateHandler.increasePage(), PaginatedMenuStateFlag.PAGE);
    }


    @Override
    public final void onChangePage(int page) {
        List<Integer> positions = IntStream.range(PAGE_ITEM_INDEX_MIN, PAGE_ITEM_INDEX_MAX)
                .boxed().filter((position) -> !isFramePosition(position))
                .collect(Collectors.toList());

        getDispatcher().clearItemsFromTo(items, PAGE_ITEM_INDEX_MIN, PAGE_ITEM_INDEX_MAX);

        List<Item> items = positions.stream()
                .map(Collection.toIndexed())
                .filter((indexed) -> !isItemOutOfBounds(indexed.getIndex(), page))
                .map((indexed) -> convertIndexedToItem(indexed, page))
                .collect(Collectors.toList());

        setItems();
        loadPaginatedItems(items);
    }


    @Override
    public final void loadPaginatedItems(@NotNull List<Item> batch) {
        batch.forEach(item -> {
            if (!(item instanceof StateItem)) {
                return;
            }

            ((StateItem<?>) item).injectDispatcher(getDispatcher());
        });
        batch.forEach(item -> setItem(item.getPosition(), item));
        batch.forEach(getDispatcher()::updateItem);
    }


    @Override
    public boolean isFramePosition(int position) {
        boolean isLeft = position % 9 == 0;
        boolean isRight = position % 9 == 8;
        boolean isTop = (position / 9.0d) <= 1.0d;
        boolean isBottom = position / (4.0d * 9.0d) >= 1.0;
        return isLeft || isRight || isTop || isBottom;
    }


    @Override
    public final boolean isItemOutOfBounds(int itemIndex, int page) {
        return calculateItemPosition(itemIndex, page) >= getItemsAmount() || itemIndex >= calculateMaxItemsPerPage(this::isFramePosition);
    }


    @Override
    public final boolean hasNextPage() {
        return calculateMaxItemsPerPage(this::isFramePosition) * stateHandler.getState().getDisplayPage() <= getItemsAmount();
    }


    @Override
    public ItemAction getPaginatedItemAction(@NotNull PI paginatedItem) {
        throw new RuntimeException("Paginated item action must be set, if using either clickable or draggable items.");
    }


    @NotNull
    @Override
    public final PreviousPageItem<S> getPreviousItem() {
        return previousItem;
    }


    @NotNull
    @Override
    public final CurrentPageItem<S> getCurrentItem() {
        return currentItem;
    }


    @NotNull
    @Override
    public final NextPageItem<S> getNextItem() {
        return nextItem;
    }


    private int getItemsAmount() {
        return paginationItems.size();
    }


    @NotNull
    private Item convertIndexedToItem(@NotNull Collection<Integer> indexed, int page) {
        int itemPosition = calculateItemPosition(indexed.getIndex(), page);
        int inventoryPosition = indexed.getValue();

        PI paginatedItem = paginationItems.get(itemPosition);
        Item item = loadPaginatedItem(paginatedItem, itemPosition);
        if (item instanceof ClickableItem) {
            ((ClickableItem) item).setAction(getPaginatedItemAction(paginatedItem));
        } else if (item instanceof DraggableItem) {
            ((DraggableItem) item).setAction(getPaginatedItemAction(paginatedItem));
        }

        item.setPosition(inventoryPosition);
        return item;
    }


    private int calculateItemPosition(int itemIndex, int page) {
        return itemIndex + page * calculateMaxItemsPerPage(this::isFramePosition);
    }


    private int calculateMaxItemsPerPage(@NotNull Function<Integer, Boolean> isFramePosition) {
        AtomicInteger max = new AtomicInteger(0);
        AtomicInteger count = new AtomicInteger(0);

        IntStream.rangeClosed(PAGE_ITEM_INDEX_MIN, PAGE_ITEM_INDEX_MAX).forEach((position) -> {
            if (isFramePosition.apply(position)) {
                max.set(Math.max(max.get(), count.get()));
                return;
            }

            count.set(count.get() + 1);
        });

        return Math.max(max.get(), count.get());
    }


    private class OpenMenuHandler implements OpenInventoryHandler {

        @Override
        public void beforeInventoryLoaded() {
            setItems();
        }


        @Override
        public void loadInventory() {
            setInventory(Bukkit.createInventory(null, getSize(), title));
        }


        @Override
        public void afterInventoryLoaded() {
            stateHandler.getState().setMaxItems(getItemsAmount());
            stateHandler.goToStartingPage();
        }


        @Override
        public void afterInventoryOpened() {
            items.values().forEach(getDispatcher()::updateItem);
        }

    }


}
