package com.github.bakuplayz.spigotspin.menu.menus.common.components;

import com.github.bakuplayz.spigotspin.menu.menus.Menu;
import lombok.AllArgsConstructor;
import org.bukkit.event.inventory.InventoryType;

@AllArgsConstructor
public final class SizeComponent {

    private final Menu menu;

    private final SizeType type;

    private final InventoryType inventoryType;


    public void handleSize() {
    }


    public int getMaxSize() {
        if (type == SizeType.CHEST) {
            return InventoryType.CHEST.getDefaultSize();
        }

        if (type == SizeType.DYNAMIC) {
            throw new UnsupportedOperationException("Not implemented yet");
        }

       /* switch (type) {
            case CONTAINER:
                return menu.getMaxSize();
        }*/
        return 0;
    }


    public enum SizeType {

        NORMAL,

        DYNAMIC,

        CHEST,

        DOUBLE_CHEST

    }

}
