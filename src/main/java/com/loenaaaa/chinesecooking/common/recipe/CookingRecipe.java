package com.loenaaaa.chinesecooking.common.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;

import org.apache.commons.lang3.tuple.Pair;

public class CookingRecipe {

    public List<ItemStack> inputItems;
    public List<ItemStack> outputItems;
    /** The recipe time in TICKS */
    public int recipeTime;
    public int minTemp = 50;
    public int maxTemp = 100;

    CookingRecipe addInput(ItemStack... inputs) {
        this.inputItems.addAll(Arrays.asList(inputs));
        return this;
    }

    CookingRecipe addOutput(ItemStack... inputs) {
        this.outputItems.addAll(Arrays.asList(inputs));
        return this;
    }

    CookingRecipe setRecipeTime(int recipeTime) {
        this.recipeTime = recipeTime;
        return this;
    }

    CookingRecipe setTemperatureRange(int minTemp, int maxTemp) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        return this;
    }

    CookingRecipe setTemperatureRange(Pair<Integer, Integer> temperatureBoundaries) {
        this.minTemp = temperatureBoundaries.getLeft();
        this.maxTemp = temperatureBoundaries.getRight();
        return this;
    }

    public static class CookingRecipeLists {

        public static ArrayList<CookingRecipe> fermenterRecipes, extractorRecipes, boilingRecipes, fryingRecipes,
            roastingRecipes, stuffingRecipes;
    }
}
