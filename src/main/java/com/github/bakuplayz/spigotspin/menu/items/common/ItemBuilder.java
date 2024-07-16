/**
 * CropClick - "A Spigot plugin aimed at making your farming faster, and more customizable."
 * <p>
 * Copyright (C) 2023 BakuPlayz
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.bakuplayz.spigotspin.menu.items.common;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.menu.common.VersionUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * A utility class to manufacture {@link ItemStack items} easier.
 *
 * @author BakuPlayz
 * @version 0.1-Beta
 * @since 0.1-Beta
 */
public final class ItemBuilder {

    private int amount;

    private String name;

    private List<String> lore;

    private Material material;

    @Nullable
    private ItemStack item;


    public ItemBuilder() {
        this(Material.AIR, "", 1, null);
    }


    public ItemBuilder(@NotNull Material material, @NotNull String name, int amount, List<String> lore) {
        this.lore = lore == null ? Collections.emptyList() : lore;
        this.material = material;
        this.amount = amount;
        this.name = name;
    }


    /**
     * Sets the {@link #amount} to the provided amount.
     *
     * @param amount the amount to set.
     */
    public void setAmount(int amount) {
        this.amount = amount <= -1 ? this.amount : amount;
    }


    /**
     * Sets the {@link #name} to the provided name.
     *
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name == null ? this.name : name;
    }


    /**
     * Sets the {@link #lore} of the item to the provided lore.
     *
     * @param lore the lore to set.
     */
    public void setLore(List<String> lore) {
        this.lore = lore == null ? this.lore : lore;
    }


    /**
     * Sets the {@link #lore} of the item to the provided lore.
     *
     * @param lore the lore to set.
     */
    public void setLore(String... lore) {
        this.lore = lore == null ? this.lore : Arrays.asList(lore);
    }


    public void setItem(@Nullable ItemStack item) {
        this.item = item;
        this.material = item == null ? Material.AIR : item.getType();
    }


    /**
     * Converts the {@link ItemBuilder item builder} to a {@link ItemStack item}.
     *
     * @return the item.
     */
    @Nullable
    public ItemStack toItemStack() {
        ItemStack stack = this.item != null ? item : new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        stack.setAmount(amount);

        if (material == Material.AIR) {
            return null;
        }

        if (meta == null) {
            return stack;
        }

        if (lore != null) {
            lore = lore.stream().map(this::colorize).collect(Collectors.toList());
            meta.setLore(lore);
        }

        if (name != null) {
            meta.setDisplayName(colorize(name));
        }

        if (isToolOrWeapon(stack)) {
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }

        if (name != null || lore != null) {
            stack.setItemMeta(meta);
        }

        return stack;
    }


    /**
     * Converts the {@link ItemBuilder item builder} to a {@link ItemStack item} of the {@link OfflinePlayer provided player's} head.
     *
     * @param player the player whose head you want to use.
     *
     * @return the head of the provided {@link OfflinePlayer}.
     */
    @Nullable
    public ItemStack toPlayerHead(@NotNull OfflinePlayer player) {
        ItemStack stack = XMaterial.PLAYER_HEAD.parseItem();
        if (stack == null) {
            return null;
        }

        SkullMeta meta = (SkullMeta) stack.getItemMeta();
        stack.setAmount(amount);

        if (meta == null) {
            return stack;
        }

        if (lore != null) {
            meta.setLore(lore);
        }

        if (name != null) {
            meta.setDisplayName(name);
        }

        if (VersionUtils.between(0, 12.9)) {
            meta.setOwner(player.getName());
        } else {
            meta.setOwningPlayer(player);
        }

        if (name != null || lore != null) {
            stack.setItemMeta(meta);
        }

        stack.setItemMeta(meta);
        return stack;
    }


    /**
     * Checks whether the passed {@link ItemStack item} is a tool or weapon.
     *
     * @param item the item to check.
     *
     * @return true if is, otherwise false.
     */
    private boolean isToolOrWeapon(@NotNull ItemStack item) {
        return EnchantmentTarget.WEAPON.includes(item) || EnchantmentTarget.TOOL.includes(item);
    }


    /**
     * Colorizes the given content using the format of '&'.
     *
     * @param content the content to colorize.
     *
     * @return the content being colorized.
     */
    @NotNull
    private String colorize(@NotNull String content) {
        return ChatColor.translateAlternateColorCodes('&', content);
    }

}