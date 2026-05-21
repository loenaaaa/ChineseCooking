package com.loenaaaa.chinesecooking.common.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import com.loenaaaa.chinesecooking.ModItems;
import com.loenaaaa.chinesecooking.utils.ItemRegistryUtils;

public class RecipeLoader {

    public static void run() {
        if (!ItemRegistryUtils.isHarvestcraftLoaded()) return;
        final float FURNACE_RECIPES_EXP = 0.3F;
        FurnaceRecipes.smelting()
            .func_151394_a(
                new ItemStack(Blocks.yellow_flower),
                ModItems.DRIED_YELLOW_FLOWER.getItemStack(),
                FURNACE_RECIPES_EXP);
        FurnaceRecipes.smelting()
            .func_151394_a(
                ItemRegistryUtils.getHarvestcraftItemStack("shrimprawItem"),
                ModItems.DRIED_SHRIMP.getItemStack(),
                FURNACE_RECIPES_EXP);
        CookingRecipe.CookingRecipeLists.fermenterRecipes.add(
            new CookingRecipe()
                .addInput(
                    ItemRegistryUtils.getHarvestcraftItemStack("gingerItem"),
                    ItemRegistryUtils.getHarvestcraftItemStack("saltItem"))
                .addOutput(ModItems.AGED_GINGER.getItemStack())
                .setRecipeTime(1000));
    }
}
