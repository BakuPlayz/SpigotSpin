package com.github.bakuplayz.spigotspin.menu.items;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.menu.items.common.ItemBuilder;
import com.github.bakuplayz.spigotspin.menu.items.common.ViewState;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

// TODO: Make the disabled be replaced with
//       and disabled item...
public abstract class Item {

    private final ItemBuilder builder;


    @Getter
    @Setter
    private int position;


    @Setter
    private ViewState viewState;


    /**
     * Should be sat if the item should be considered as
     * a player item. And then take the head of the provided
     * player.
     */
    @Setter
    @Nullable
    private OfflinePlayer player;


    protected Item() {
        this.builder = new ItemBuilder();
        this.viewState = ViewState.VISIBLE;
    }


    public abstract void create();


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


    public void setName(boolean shouldSetName, @NotNull String name) {
        if (shouldSetName) setName(name);
    }


    public void setMaterial(@NotNull XMaterial material) {
        setMaterial(material, XMaterial.STICK);
    }


    public void setMaterial(@NotNull XMaterial material, @NotNull XMaterial def) {
        builder.setItem(material.or(def).parseItem());
    }


    public void setMaterial(boolean shouldSetMaterial, @NotNull XMaterial material) {
        if (shouldSetMaterial) setMaterial(material);
    }


    public ItemStack asItemStack() {
        if (viewState == ViewState.INVISIBLE) {
            return null;
        }
        if (viewState != ViewState.DISABLED && player != null) {
            return builder.toPlayerHead(player);
        }
        return builder.toItemStack();
    }


}
