package examples.com.github.bakuplayz.spigotspin.plain;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.menu.items.ClickableItem;

public class ExampleItem extends ClickableItem {

    @Override
    public void create() {
        setName("Test");
        setMaterial(XMaterial.ANVIL);
    }

}
