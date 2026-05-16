package com.loenaaaa.chinesecooking;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.loenaaaa.chinesecooking.utils.ItemRegistryUtils;

import cpw.mods.fml.common.registry.GameRegistry;

public enum ModItems {

    // spotless:off
    AGED_GINGER("aged_ginger"),
    BEEF_BONE("beef_bone"),
    CATASTROPHIC_CHILIES("catastrophic_chilies"),
    CHICKEN_BONE("chicken_bone"),
    CORNSTARCH("cornstarch"),
    DARK_SOY_SAUCE("dark_soy_sauce"),
    DRIED_SHRIMP("dried_shrimp"),
    DRIED_YELLOW_FLOWER("dried_yellow_flower"),
    FISH_BONE("fish_bone"),
    FISH_SKIN("fish_skin"),
    JINHUA_HAM("jinhua_ham"),
    LAMB_FAT("lamb_fat"),
    PORK_BONE("pork_bone"),
    PORK_FAT("pork_fat"),
    PORK_GUTS("pork_guts"),
    RICE_FLOUR("rice_flour"),
    RICE_VINEGAR("rice_vinegar"),
    RICE_WINE("rice_wine"),
    ROCK_SUGAR("rock_sugar"),
    SESAME_OIL("sesame_oil"),
    TOFU_SKIN("tofu_skin"),
    // spotless:on
    ;

    private final Item item;
    private final String registryName;

    ModItems(String standardizedName) {
        this(ItemRegistryUtils.getChinesecookingBasicItem(standardizedName), standardizedName);
    }

    ModItems(Item item, String registryName) {
        this.item = item;
        this.registryName = registryName;
    }

    public static void init() {
        for (ModItems modItem : values()) {
            GameRegistry.registerItem(modItem.item, modItem.registryName);
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
