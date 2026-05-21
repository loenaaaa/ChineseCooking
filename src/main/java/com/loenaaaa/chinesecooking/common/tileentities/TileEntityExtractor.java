package com.loenaaaa.chinesecooking.common.tileentities;

import com.loenaaaa.chinesecooking.common.recipe.CookingRecipe;

public class TileEntityExtractor extends TileEntityCookingMachineStandard {

    public TileEntityExtractor() {
        super("extractor", 1, 4, CookingRecipe.CookingRecipeLists.extractorRecipes);
    }

    static {
        addMapping(TileEntityExtractor.class, "extractor");
    }
}
