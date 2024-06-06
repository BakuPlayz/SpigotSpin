package dev.bakuplayz.spigotspin.examples.plain;

import com.cryptomorin.xseries.XMaterial;
import dev.bakuplayz.spigotspin.abstraction.menu.items.ClickableItem;

public class ExampleItem extends ClickableItem {

    public ExampleItem() {
        setName("Test");
        setMaterial(XMaterial.ANVIL);
    }

}
