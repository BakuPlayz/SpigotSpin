package com.github.bakuplayz.spigotspin.abstraction.menu.items;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.abstraction.menu.dispatchers.HistoryDispatcher;

public class BackItem extends ClickableItem {

    public BackItem() {
        setName("Back");
        setMaterial(XMaterial.BARRIER);
        setAction(((item, player) -> HistoryDispatcher.popBackStack(player)));
    }

}
