package com.loenaaaa.chinesecooking;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.loenaaaa.chinesecooking.common.blocks.BlockCookingMachineStandard;

import cpw.mods.fml.common.registry.GameRegistry;

public enum ModBlocks {

    // spotless:off
    BARREL("barrel", false),
    EXTRACTOR("extractor",false),
    STOVE("stove",true),
    //spotless:on
    ;

    private final Block block;

    private final String registryName;

    ModBlocks(String standardizedName, boolean hasFront) {
        this(new BlockCookingMachineStandard(standardizedName, hasFront), standardizedName);
    }

    ModBlocks(Block block, String registryName) {
        this.block = block;
        this.registryName = registryName;
    }

    public static void init() {
        for (ModBlocks modBlock : values()) {
            GameRegistry.registerBlock(modBlock.block, modBlock.registryName);
        }
    }

    public Block getBlock() {
        return block;
    }

    public String getRegistryName() {
        return registryName;
    }

    public Item getItem() {
        return Item.getItemFromBlock(block);
    }

    public ItemStack getItemStack(int count) {
        return new ItemStack(getItem(), count);
    }

    public ItemStack getItemStack(int count, int meta) {
        return new ItemStack(getItem(), count, meta);
    }
}
