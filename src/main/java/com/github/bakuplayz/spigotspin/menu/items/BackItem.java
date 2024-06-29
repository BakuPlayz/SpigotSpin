package com.github.bakuplayz.spigotspin.menu.items;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.SpigotSpin;

public class BackItem extends ClickableItem {

    public BackItem() {
        setName("Back");
        setMaterial(XMaterial.BARRIER);
        setAction(((item, player) -> SpigotSpin.Manager.REF.getHistory().popBackStack(player)));
    }

}
