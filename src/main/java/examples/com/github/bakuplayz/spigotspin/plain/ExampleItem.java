package examples.com.github.bakuplayz.spigotspin.plain;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.menu.items.ClickableItem;

import java.util.concurrent.CompletableFuture;

public class ExampleItem extends ClickableItem {

    @Override
    public CompletableFuture<Void> create() {
        return createSync(() -> {
            setName("Test");
            setMaterial(XMaterial.ANVIL);
        });
    }

}
