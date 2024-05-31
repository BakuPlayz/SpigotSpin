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

package dev.bakuplayz.spigotspin.abstraction.items.utils;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
 * @version 2.0.0
 * @since 2.0.0
 */
@Getter
public final class ItemBuilder {

    private int amount;

    private ItemMeta meta;
    private Material material;

    private String name;
    private List<String> lore;


    public ItemBuilder() {
        this(Material.AIR, "", 1, null);
    }


    public ItemBuilder(@NotNull Material material) {
        this(material, "", 1, null);
    }


    public ItemBuilder(@NotNull Material material, int amount) {
        this(material, "", amount, null);
    }


    public ItemBuilder(@NotNull Material material, @NotNull String name) {
        this(material, name, 1, null);
    }


    public ItemBuilder(@NotNull Material material, @NotNull String name, int amount) {
        this(material, name, amount, null);
    }


    public ItemBuilder(@NotNull Material material, @NotNull String name, int amount, List<String> lore) {
        this.lore = lore == null ? Collections.emptyList() : lore;
        this.material = material;
        this.amount = amount;
        this.name = name;
    }


    public ItemBuilder(@NotNull ItemStack stack) {
        if (stack.hasItemMeta()) {
            this.meta = stack.getItemMeta();
        }
        this.amount = stack.getAmount();
        this.material = stack.getType();
        this.lore = meta != null && meta.hasLore()
                ? meta.getLore()
                : Collections.emptyList();
    }


    public ItemBuilder(@NotNull ItemStack stack, int amount) {
        this(stack);
        this.amount = amount;
    }


    public ItemBuilder(@NotNull ItemStack stack, @NotNull String name) {
        this(stack);
        this.name = name;
    }


    public ItemBuilder(@NotNull ItemStack stack, @NotNull String name, int amount) {
        this(stack, amount);
        this.name = name;
    }


    public ItemBuilder(@NotNull ItemStack stack, @NotNull String name, int amount, List<String> lore) {
        this(stack, name, amount);
        this.lore = lore;
    }


    /**
     * Sets the {@link #amount} to the provided amount.
     *
     * @param amount the amount to set.
     * @return the {@link ItemBuilder ItemBuilder instance}.
     */
    public ItemBuilder setAmount(int amount) {
        this.amount = amount <= -1 ? this.amount : amount;
        return this;
    }


    /**
     * Sets the {@link #material} to the {@link Material provided material}.
     *
     * @param material the material to set.
     * @return the {@link ItemBuilder ItemBuilder instance}.
     */
    public ItemBuilder setMaterial(Material material) {
        this.material = material == null ? this.material : material;
        return this;
    }


    /**
     * Sets the {@link #material} to the {@link Material provided material}, if it matches the condition.
     *
     * @param condition the condition to match.
     * @param material  the material to set.
     * @return the {@link ItemBuilder ItemBuilder instance}.
     */
    public ItemBuilder setMaterial(boolean condition, Material material) {
        if (condition) {
            setMaterial(material);
        }
        return this;
    }


    /**
     * Sets the {@link #name} to the provided name.
     *
     * @param name the name to set.
     * @return the {@link ItemBuilder ItemBuilder instance}.
     */
    public ItemBuilder setName(String name) {
        this.name = name == null ? this.name : name;
        return this;
    }


    /**
     * Sets the {@link #lore} of the item to the {@link List<String> provided lore}.
     *
     * @param lore the lore to set.
     * @return the {@link ItemBuilder ItemBuilder instance}.
     */
    public ItemBuilder setLore(List<String> lore) {
        this.lore = lore == null ? this.lore : lore;
        return this;
    }


    /**
     * Sets the {@link #lore} of the item to the provided lore.
     *
     * @param lore the lore to set.
     * @return the {@link ItemBuilder ItemBuilder instance}.
     */
    public ItemBuilder setLore(String... lore) {
        this.lore = lore == null ? this.lore : Arrays.asList(lore);
        return this;
    }


    private @NotNull String colorize(@NotNull String content) {
        return ChatColor.translateAlternateColorCodes('&', content);
    }


    /**
     * Converts the {@link ItemBuilder item builder} to a {@link ItemStack item}.
     *
     * @return the item.
     */
    @Nullable
    public ItemStack toItemStack() {
        ItemStack stack = new ItemStack(material, amount);
        ItemMeta meta = this.meta != null ? this.meta : stack.getItemMeta();

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

        if (material == Material.AIR) {
            return null;
        }

        return stack;
    }


    /**
     * Checks whether the passed {@link ItemStack item} is a tool or weapon.
     *
     * @param item the item to check.
     * @return true if is, otherwise false.
     */
    private boolean isToolOrWeapon(@NotNull ItemStack item) {
        return EnchantmentTarget.WEAPON.includes(item) || EnchantmentTarget.TOOL.includes(item);
    }

}