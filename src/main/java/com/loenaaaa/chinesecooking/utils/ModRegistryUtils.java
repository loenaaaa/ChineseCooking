package com.loenaaaa.chinesecooking.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRegistryUtils {

    public static String harvestcraftModID = "harvestcraft";

    public static boolean isHarvestcraftLoaded() {
        return Loader.isModLoaded(harvestcraftModID);
    }

    public static Item getHarvestcraftItem(String registryName) {
        final Item harvestcraftItem = GameRegistry.findItem(harvestcraftModID, registryName);
        if (harvestcraftItem == null)
            throw new RuntimeException("Could not find Harvestcraft item " + registryName + " !");
        return harvestcraftItem;
    }

    public static ItemStack getHarvestcraftItemStack(String registryName) {
        return new ItemStack(getHarvestcraftItem(registryName));
    }

    public static ItemStack getHarvestcraftItemStack(String registryName, int stackSize) {
        return new ItemStack(getHarvestcraftItem(registryName), stackSize);
    }

    public static ItemStack getHarvestcraftItemStack(String registryName, int stackSize, int meta) {
        return new ItemStack(getHarvestcraftItem(registryName), stackSize, meta);
    }
}
