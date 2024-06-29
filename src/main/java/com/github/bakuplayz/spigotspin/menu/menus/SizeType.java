package com.github.bakuplayz.spigotspin.menu.menus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SizeType {

    /**
     * State for describing invalid/non-supported menu,
     * that is yet to be implemented or such.
     */
    INVALID(-1),

    /**
     * State for creating a dynamic menu, i.e. a menu
     * with x * 9 slots, where the x is determined by
     * the placements of the inventory to fit all items.
     **/
    DYNAMIC(54),

    /**
     * State for creating a normal chest.
     */
    CHEST(27),

    /**
     * State for creating a dispenser.
     */
    DISPENSER(9),

    /**
     * State for creating a double chest.
     */
    DOUBLE_CHEST(54),

    /**
     * State for creating a shulker box, will fail
     * unless the plugin uses a shulker box supported
     * version.
     */
    SHULKER_BOX(27);

    private final int maxSize;

}
