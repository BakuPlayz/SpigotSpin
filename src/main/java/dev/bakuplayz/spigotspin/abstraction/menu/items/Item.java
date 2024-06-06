package dev.bakuplayz.spigotspin.abstraction.menu.items;

import com.cryptomorin.xseries.XMaterial;
import dev.bakuplayz.spigotspin.abstraction.menu.items.utils.ItemBuilder;
import dev.bakuplayz.spigotspin.abstraction.menu.items.utils.ViewState;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class Item {

    private final ItemBuilder builder;


    @Getter
    @Setter
    private int position;


    @Setter
    private ViewState viewState;


    public Item() {
        this.builder = new ItemBuilder();
        this.viewState = ViewState.VISIBLE;
    }


    public void setLore(@NotNull String... lore) {
        builder.setLore(lore);
    }


    public void setLore(@NotNull List<String> lore) {
        builder.setLore(lore);
    }


    public void setAmount(int amount) {
        builder.setAmount(amount);
    }


    public void setName(@NotNull String name) {
        builder.setName(name);
    }


    public void setMaterial(@NotNull XMaterial material) {
        builder.setMaterial(material.or(XMaterial.STICK).parseMaterial());
    }


    public ItemStack asItemStack() {
        if (viewState == ViewState.INVISIBLE) {
            return null;
        }
        return builder.toItemStack();
    }


}
