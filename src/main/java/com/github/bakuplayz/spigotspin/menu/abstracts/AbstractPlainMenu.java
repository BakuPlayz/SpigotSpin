package com.github.bakuplayz.spigotspin.menu.abstracts;

import com.github.bakuplayz.spigotspin.menu.common.SizeType;
import com.github.bakuplayz.spigotspin.menu.common.plain.PlainMenu;
import com.github.bakuplayz.spigotspin.menu.items.ClickableItem;
import com.github.bakuplayz.spigotspin.menu.items.DraggableItem;
import com.github.bakuplayz.spigotspin.menu.items.Item;
import com.github.bakuplayz.spigotspin.menu.items.actions.ClickableAction;
import com.github.bakuplayz.spigotspin.menu.items.actions.DraggableAction;
import com.github.bakuplayz.spigotspin.menu.utils.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

public abstract class AbstractPlainMenu extends AbstractMenu implements PlainMenu {

    private final String title;


    public AbstractPlainMenu(@NotNull String title) {
        this.title = title;
    }


    protected final void validatePosition(int position) {
        Preconditions.validateArgumentBounds(position, 0, getMaxSize(), "Position out of bounds: %s.");
    }


    @Override
    public final void setItem(int position, @NotNull Item item) {
        validatePosition(position);
        item.setPosition(position);
        items.put(position, item);
    }


    @Override
    public void setItemIf(boolean setIfTrue, int position, @NotNull Item item) {
        if (setIfTrue) setItem(position, item);
    }


    @Override
    public final void setItem(int position, @NotNull DraggableItem item, @NotNull DraggableAction action) {
        validatePosition(position);
        item.setPosition(position);
        item.setAction(action);
        items.put(position, item);
    }


    @Override
    public void setItemIf(boolean setIfTrue, int position, @NotNull DraggableItem item, @NotNull DraggableAction action) {
        if (setIfTrue) setItem(position, item, action);
    }


    @Override
    public <I extends ClickableItem> void setItem(int position, @NotNull I item, @NotNull ClickableAction<I> action) {
        validatePosition(position);
        item.setPosition(position);
        item.setAction(action);
        items.put(position, item);
    }


    @Override
    public <I extends ClickableItem> void setItemIf(boolean setIfTrue, int position, @NotNull I item, @NotNull ClickableAction<I> action) {
        if (setIfTrue) setItem(position, item, action);
    }


    @Override
    public final int getMaxSize() {
        return getSizeType().getMaxSize();
    }


    @Override
    public final int getSize() {
        /* We don't have a dynamic type, meaning
         * we must have a fixed type get the max
         * since this size is the same.       */
        if (getSizeType() != SizeType.DYNAMIC) {
            return getMaxSize();
        }

        int lastIndex = IntStream.range(0, getMaxSize())
                .filter(position -> getItems().get(position) != null)
                .reduce((first, second) -> second)
                .orElse(0);

        return (int) Math.ceil(lastIndex / 9.0d) * 9;
    }


    @NotNull
    @Override
    public Inventory createInventory() {
        switch (getSizeType()) {
            case DYNAMIC:
            case DOUBLE_CHEST:
                return Bukkit.createInventory(null, getSize(), title);

            case CHEST:
                return Bukkit.createInventory(null, InventoryType.CHEST, title);

            case DISPENSER:
                return Bukkit.createInventory(null, InventoryType.DISPENSER, title);

            case SHULKER_BOX:
                return Bukkit.createInventory(null, InventoryType.SHULKER_BOX, title);

            default:
                throw new IllegalArgumentException("Unsupported type cannot create inventory for an invalid type.");
        }
    }


    @Override
    public void onPoppedTo() {
        // Do nothing
    }


    @Override
    public boolean isFramePosition(int position) {
        return false;
    }


    @Override
    public Item getFrameItem(int position) {
        return null;
    }


    @Override
    public void setFrameItems() {
        IntStream.range(ITEM_MIN_AMOUNT, getMaxSize()).forEach((position) -> {
            Item item = getFrameItem(position);
            if (item != null && isFramePosition(position)) {
                setItem(position, item);
            }
        });
    }

}
