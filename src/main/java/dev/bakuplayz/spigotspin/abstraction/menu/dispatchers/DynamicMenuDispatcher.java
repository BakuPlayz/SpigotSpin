package dev.bakuplayz.spigotspin.abstraction.menu.dispatchers;

import dev.bakuplayz.spigotspin.abstraction.menu.items.Item;
import dev.bakuplayz.spigotspin.abstraction.menu.utils.LazyEvaluator;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.stream.IntStream;

public final class DynamicMenuDispatcher {

    @NotNull
    private final LazyEvaluator<Inventory> lazyInventory;


    public DynamicMenuDispatcher(@NotNull LazyEvaluator<Inventory> lazyInventory) {
        this.lazyInventory = lazyInventory;
    }


    public void updateItem(@NotNull Item item) {
        lazyInventory.get().setItem(item.getPosition(), item.asItemStack());
        lazyInventory.get().getViewers().forEach(viewer -> ((Player) viewer).updateInventory());
    }


    public void clearItemsFromTo(@NotNull Map<Integer, Item> items, int min, int max) {
        IntStream.range(min, max).forEach(position -> lazyInventory.get().setItem(position, null));
        IntStream.range(min, max).forEach(items::remove);
    }

}
