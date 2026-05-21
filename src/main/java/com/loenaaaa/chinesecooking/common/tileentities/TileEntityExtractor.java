package com.loenaaaa.chinesecooking.common.tileentities;

import java.util.ArrayList;

import com.loenaaaa.chinesecooking.common.recipe.CookingRecipe;

public class TileEntityExtractor extends TileEntityCookingMachineStandard {

    public TileEntityExtractor() {
        super("extractor", 1, 4);
    }

    @Override
    public ArrayList<CookingRecipe> getRecipeList() {
        return CookingRecipe.CookingRecipeLists.extractorRecipes;
    }

    static {
        addMapping(TileEntityExtractor.class, "extractor");
    }
}
