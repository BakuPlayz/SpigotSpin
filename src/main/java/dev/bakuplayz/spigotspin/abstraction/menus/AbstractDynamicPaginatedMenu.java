package dev.bakuplayz.spigotspin.abstraction.menus;

import dev.bakuplayz.spigotspin.abstraction.items.Item;
import dev.bakuplayz.spigotspin.abstraction.items.paginated.CurrentPageItem;
import dev.bakuplayz.spigotspin.abstraction.items.paginated.NextPageItem;
import dev.bakuplayz.spigotspin.abstraction.items.paginated.PreviousPageItem;
import dev.bakuplayz.spigotspin.abstraction.items.state.StateItem;
import dev.bakuplayz.spigotspin.abstraction.menus.handlers.OpenInventoryHandler;
import dev.bakuplayz.spigotspin.abstraction.menus.paginated.*;
import dev.bakuplayz.spigotspin.abstraction.utils.Collection;
import dev.bakuplayz.spigotspin.abstraction.utils.TypeUtils;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public abstract class AbstractDynamicPaginatedMenu<S extends PaginatedMenuState, SH extends PaginatedMenuStateHandler<S>>
        extends AbstractDynamicStateMenu<S> implements DynamicPaginatedMenu, PaginatedMenuStateObserver<S> {

    public static final int STARTING_PAGE = 0;

    public static final int PAGE_ITEM_INDEX_MIN = 0;

    public static final int PAGE_ITEM_INDEX_MAX = 45;


    @Getter
    @NotNull
    private final NextPageItem<PaginatedMenuState> nextItem;

    @Getter
    @NotNull
    private final CurrentPageItem<PaginatedMenuState> currentItem;

    @Getter
    @NotNull
    private final PreviousPageItem<PaginatedMenuState> previousItem;

    @Setter
    @NotNull
    protected SH stateHandler;


    protected AbstractDynamicPaginatedMenu(@NotNull String title) {
        super(title);

        this.currentItem = new CurrentPageItem<>();
        this.previousItem = new PreviousPageItem<>();
        this.nextItem = new NextPageItem<>(this);
    }


    @Override
    public void open(@NotNull Player player) {
        open(player, new OpenMenuHandler());
    }


    @Override
    public void setItems() {
        setItem(48, TypeUtils.infer(getPreviousItem()), (ignored) -> stateHandler.decreasePage(), PaginatedMenuStateFlag.PAGE);
        setItem(49, TypeUtils.infer(getCurrentItem()), PaginatedMenuStateFlag.PAGE);
        setItem(50, TypeUtils.infer(getNextItem()), (ignored) -> stateHandler.increasePage(), PaginatedMenuStateFlag.PAGE);
    }


    @Override
    public final void onChangePage(int page) {
        List<Integer> positions = IntStream.range(PAGE_ITEM_INDEX_MIN, PAGE_ITEM_INDEX_MAX)
                .boxed().filter((position) -> !isFramePosition(position))
                .collect(Collectors.toList());

        dispatcher.clearItemsFromTo(items, PAGE_ITEM_INDEX_MIN, PAGE_ITEM_INDEX_MAX);

        List<Item> items = positions.stream()
                .map(Collection.toIndexed())
                .filter((indexed) -> !isItemOutOfBounds(indexed.getIndex(), page))
                .map((indexed) -> convertIndexedToItem(indexed, page))
                .collect(Collectors.toList());

        setItems();
        loadItems(items);
    }


    @Override
    public final void loadItems(@NotNull List<Item> batch) {
        batch.forEach(item -> {
            if (!(item instanceof StateItem)) {
                return;
            }

            ((StateItem<?>) item).injectDispatcher(dispatcher);
        });
        batch.forEach(item -> setItem(item.getPosition(), item));
        batch.forEach(dispatcher::updateItem);
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
    public boolean hasNextPage() {
        return calculateMaxItemsPerPage(this::isFramePosition) * stateHandler.getState().getDisplayPage() <= getItemsAmount();
    }


    @NotNull
    private Item convertIndexedToItem(@NotNull Collection<Integer> indexed, int page) {
        int inventoryPosition = indexed.getValue();
        int itemPosition = calculateItemPosition(indexed.getValue(), page);
        return loadItem(itemPosition, inventoryPosition, page, page + 1);
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
            setInventory(Bukkit.createInventory(AbstractDynamicPaginatedMenu.this, getSize(), title));
        }


        @Override
        public void afterInventoryLoaded() {
            stateHandler.getState().setMaxItems(getItemsAmount());
            onChangePage(STARTING_PAGE);
        }


        @Override
        public void afterInventoryOpened() {
            items.values().forEach(dispatcher::updateItem);
        }

    }


}
