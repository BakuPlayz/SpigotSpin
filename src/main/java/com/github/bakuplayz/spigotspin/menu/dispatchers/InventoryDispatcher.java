package com.github.bakuplayz.spigotspin.menu.dispatchers;

import com.github.bakuplayz.spigotspin.menu.common.LazyEvaluator;
import com.github.bakuplayz.spigotspin.menu.items.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.stream.IntStream;

public final class InventoryDispatcher {

    @NotNull
    private final LazyEvaluator<Inventory> lazyInventory;


    public InventoryDispatcher(@NotNull LazyEvaluator<Inventory> lazyInventory) {
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
