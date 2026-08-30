package examples.com.github.bakuplayz.spigotspin.state;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.menu.items.state.ClickableStateItem;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public final class ExampleItem extends ClickableStateItem<ExampleState> {

    private final String name;


    @Override
    public void update(@NotNull ExampleState state, int flag) {
        setLore(String.format("Count: %d", state.getCount()));
    }


    @NotNull
    @Override
    public CompletableFuture<Void> create() {
        return createSync(() -> {
            setName(String.format("&e%s", name));
            setMaterial(XMaterial.ANVIL);
        });
    }

}
