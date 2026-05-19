package com.loenaaaa.chinesecooking;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.loenaaaa.chinesecooking.utils.ItemRegistryUtils;

import cpw.mods.fml.common.registry.GameRegistry;

public enum ModItems {

    // spotless:off
    AGED_GINGER("aged_ginger"),
    ALKALI_DOUGH("alkali_dough"),
    ALKALI_NOODLES("alkali_noodles"),
    AROMATIC_BASE("aromatic_base"),
    AROMATIC_CARP("aromatic_carp"),
    AROMATIC_CHICKEN("aromatic_chicken"),
    BEAN_SAUCE("bean_sauce"),
    BEEF_BONE("beef_bone"),
    BEEF_SOUP("beef_soup"),
    BEEF_STOCK("beef_stock"),
    BLANCHED_BEEF("blanched_beef"),
    BLANCHED_CABBAGE("blanched_cabbage"),
    BLANCHED_CHICKEN("blanched_chicken"),
    BLANCHED_DUCK("blanched_duck"),
    BLANCHED_PORK("blanched_pork"),
    BLOOD_SAUSAGE("blood_sausage"),
    BLOOMED_MUSHROOM("bloomed_mushroom"),
    BREADED_PORK_WITH_RICE_FLOUR("breaded_pork_with_rice_flour"),
    CATASTROPHIC_CHILIES("catastrophic_chilies"),
    CERAMIC_JAR("ceramic_jar"),
    CHARRED_PORK_BELLY("charred_pork_belly"),
    CHICKEN_BONE("chicken_bone"),
    CHICKEN_RICE("chicken_rice"),
    CHICKEN_STOCK("chicken_stock"),
    CHILI_OIL("chili_oil"),
    CHILI_SAUCE("chili_sauce"),
    COOKED_RICE_VERMICELLI("cooked_rice_vermicelli"),
    CORNSTARCH("cornstarch"),
    CORNSTARCH_SLURRY("cornstarch_slurry"),
    CRISP_CARP("crisp_carp"),
    CRISPY_CARP("crispy_carp"),
    CRISPY_MANDARIN_FISH("crispy_mandarin_fish"),
    CUMIN("cumin"),
    CURED_MANDARIN_FISH("cured_mandarin_fish"),
    CURED_RADISH("cured_radish"),
    CUTTING_BOARD("cutting_board"),
    DARK_SOY_SAUCE("dark_soy_sauce"),
    DEEP_FRIED_PORK("deep_fried_pork"),
    DRIED_SHRIMP("dried_shrimp"),
    DRIED_YELLOW_FLOWER("dried_yellow_flower"),
    EGG_DOUGH("egg_dough"),
    FISH_BONE("fish_bone"),
    FISH_SKIN("fish_skin"),
    FISH_STOCK("fish_stock"),
    FIVE_SPICE("five_spice"),
    FLAG_NOODLES("flag_noodles"),
    FLAG_NOODLES_SAUCE("flag_noodles_sauce"),
    FRAGRANT_LAMB("fragrant_lamb"),
    FRESH_RICE("fresh_rice"),
    FRIED_ALKALI_NOODLES("fried_alkali_noodles"),
    GLUTINOUS_RICE("glutinous_rice"),
    HOT_AND_SOUR_SOUP_BASE("hot_and_sour_soup_base"),
    HOT_AROMATIC_CHICKEN("hot_aromatic_chicken"),
    HOT_POT_SAUCE("hot_pot_sauce"),
    INFUSED_LAMB("infused_lamb"),
    JINHUA_HAM("jinhua_ham"),
    JUMPING_SOUP_BASE("jumping_soup_base"),
    LAMB_DUMPLING("lamb_dumpling"),
    LAMB_DUMPLING_FILLING("lamb_dumpling_filling"),
    LAMB_FAT("lamb_fat"),
    LAMB_SEASONING("lamb_seasoning"),
    MALTOSE_SYRUP("maltose_syrup"),
    MAPO_TOFU_SAUCE("mapo_tofu_sauce"),
    MARINATED_CARP("marinated_carp"),
    MARINATED_DUCK("marinated_duck"),
    MARINATED_PORK("marinated_pork"),
    MARINATED_SEA_BASS("marinated_sea_bass"),
    MEAT_HANGAR("meat_hangar"),
    NOODLES("noodles"),
    PORK_BONE("pork_bone"),
    PORK_DUMPLING("pork_dumpling"),
    PORK_DUMPLING_FILLING("pork_dumpling_filling"),
    PORK_FAT("pork_fat"),
    PORK_GUTS("pork_guts"),
    PORK_STOCK("pork_stock"),
    RAW_BLOOD_SAUSAGE("raw_blood_sausage"),
    RAW_CATS_EARS("raw_cats_ears"),
    RAW_DUCK("raw_duck"),
    RICE_FLOUR("rice_flour"),
    RICE_VERMICELLI("rice_vermicelli"),
    RICE_VINEGAR("rice_vinegar"),
    RICE_WINE("rice_wine"),
    ROASTED_BONE("roasted_bone"),
    ROCK_SUGAR("rock_sugar"),
    SALTWATER("saltwater"),
    SEASONED_BEEF_STOCK("seasoned_beef_stock"),
    SESAME_OIL("sesame_oil"),
    SHRIMP_WONTON("shrimp_wonton"),
    SHRIMP_WONTON_FILLING("shrimp_wonton_filling"),
    SOY_RICE_SAUCE("soy_rice_sauce"),
    STIR_FRIED_TOMATO("stir_fried_tomato"),
    STUFFED_DUCK("stuffed_duck"),
    SUCCULENT_SOUP_BASE("succulent_soup_base"),
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
