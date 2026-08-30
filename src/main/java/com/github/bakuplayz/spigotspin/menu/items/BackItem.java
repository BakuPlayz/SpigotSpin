package com.github.bakuplayz.spigotspin.menu.items;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.SpigotSpin;

import java.util.concurrent.CompletableFuture;

public class BackItem extends ClickableItem {

    @Override
    public CompletableFuture<Void> create() {
        return createSync(() -> {
            setName("Back");
            setMaterial(XMaterial.BARRIER);
            setAction(((item, player) -> SpigotSpin.Manager.REF.getHistory().popBackStack(player)));
        });
    }

}
