package com.github.bakuplayz.spigotspin.menu.items;

import java.util.HashMap;

public final class ItemsMap extends HashMap<Integer, Item> {

    public ItemsMap(int size) {
        super((int) (size / 0.75 + 1));
    }

}
