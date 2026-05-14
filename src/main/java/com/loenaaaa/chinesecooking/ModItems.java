package com.loenaaaa.chinesecooking;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.registry.GameRegistry;

public enum ModItems {

    // spotless:off
    DRIED_YELLOW_FLOWER(new Item().setTextureName("chinesecooking:dried_yellow_flower").setUnlocalizedName("dried_yellow_flower"),"dried_yellow_flower"),
    // spotless:on
    ;

    private final Item item;
    private final String name;

    ModItems(Item item, String name) {
        this.item = item;
        this.name = name;
    }

    public static void init() {
        for (ModItems modItem : values()) {
            GameRegistry.registerItem(modItem.item, modItem.name);
        }
    }

    public Item getItem() {
        return this.item;
    }

    public ItemStack getItemStack(int count) {
        return new ItemStack(this.item, count);
    }

    public ItemStack getItemStack(int count, int meta) {
        return new ItemStack(this.item, count, meta);
    }
}
