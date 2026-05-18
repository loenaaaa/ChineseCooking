package com.loenaaaa.chinesecooking;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.loenaaaa.chinesecooking.utils.ItemRegistryUtils;

import cpw.mods.fml.common.registry.GameRegistry;

public enum ModItems {

    // spotless:off
    AGED_GINGER("aged_ginger"),
    ALKALI_DOUGH("alkali_dough"),
    AROMATIC_BASE("aromatic_base"),
    BEAN_SAUCE("bean_sauce"),
    BEEF_BONE("beef_bone"),
    BEEF_STOCK("beef_stock"),
    BLANCHED_PORK("blanched_pork"),
    CATASTROPHIC_CHILIES("catastrophic_chilies"),
    CHICKEN_BONE("chicken_bone"),
    CHICKEN_STOCK("chicken_stock"),
    CHILI_OIL("chili_oil"),
    CHILI_SAUCE("chili_sauce"),
    COOKED_RICE_VERMICELLI("cooked_rice_vermicelli"),
    CORNSTARCH("cornstarch"),
    CORNSTARCH_SLURRY("cornstarch_slurry"),
    CRISP_CARP("crisp_carp"),
    CRISPY_CARP("crispy_carp"),
    CUMIN("cumin"),
    CURED_RADISH("cured_radish"),
    CUTTING_BOARD("cutting_board"),
    DARK_SOY_SAUCE("dark_soy_sauce"),
    DRIED_SHRIMP("dried_shrimp"),
    DRIED_YELLOW_FLOWER("dried_yellow_flower"),
    EGG_DOUGH("egg_dough"),
    FISH_BONE("fish_bone"),
    FISH_SKIN("fish_skin"),
    FISH_STOCK("fish_stock"),
    FIVE_SPICE("five_spice"),
    FLAG_NOODLES("flag_noodles"),
    FLAG_NOODLES_SAUCE("flag_noodles_sauce"),
    FRESH_RICE("fresh_rice"),
    GLUTINOUS_RICE("glutinous_rice"),
    HOT_POT_SAUCE("hot_pot_sauce"),
    JINHUA_HAM("jinhua_ham"),
    LAMB_FAT("lamb_fat"),
    LAMB_SEASONING("lamb_seasoning"),
    MEAT_HANGAR("meat_hangar"),
    NOODLES("noodles"),
    PORK_BONE("pork_bone"),
    PORK_FAT("pork_fat"),
    PORK_GUTS("pork_guts"),
    RAW_DUCK("raw_duck"),
    RICE_FLOUR("rice_flour"),
    RICE_VERMICELLI("rice_vermicelli"),
    RICE_VINEGAR("rice_vinegar"),
    RICE_WINE("rice_wine"),
    ROCK_SUGAR("rock_sugar"),
    SALTWATER("saltwater"),
    SESAME_OIL("sesame_oil"),
    TOFU_SKIN("tofu_skin"),
    WOK("wok"),
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
