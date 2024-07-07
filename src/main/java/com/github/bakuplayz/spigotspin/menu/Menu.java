package com.github.bakuplayz.spigotspin.menu;

import com.github.bakuplayz.spigotspin.menu.common.DragState;
import com.github.bakuplayz.spigotspin.menu.common.SizeType;
import com.github.bakuplayz.spigotspin.menu.common.handlers.OpenInventoryHandler;
import com.github.bakuplayz.spigotspin.menu.items.Item;
import com.github.bakuplayz.spigotspin.menu.listeners.MenuHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

public interface Menu extends MenuHandler {

    String IDENTIFIER = UUID.randomUUID().toString();

    int ITEM_MIN_AMOUNT = 0;


    void open(@NotNull Player player, @NotNull OpenInventoryHandler handler);


    void open(@NotNull Player player);


    void close(@NotNull Player player);


    void onPoppedTo();


    boolean isFramePosition(int position);


    Item getFrameItem(int position);


    void setFrameItems();


    void setItems();


    /*
     * Renders all items, might be useful for working with
     * #setItemIf conditionally on states that can be sat when
     * an item is pressed.
     * */
    void rerender();


    Map<Integer, Item> getItems();


    int getSize();


    int getMaxSize();


    Inventory getInventory();


    default DragState getDragState() {
        return DragState.DISABLED;
    }


    default SizeType getSizeType() {
        return SizeType.INVALID;
    }

}
