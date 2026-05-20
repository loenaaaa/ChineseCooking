package com.loenaaaa.chinesecooking.common.tileentities;

import com.loenaaaa.chinesecooking.common.recipe.CookingRecipe;

public class TileEntityCookingStove extends TileEntityCookingMachineStandard {

    public TileEntityCookingStove() {
        super("stove", 8, 1, CookingRecipe.CookingRecipeLists.fryingRecipes); // TODO: change recipe list to be dynamic
                                                                              // based on tool used
    }

    static {
        addMapping(TileEntityCookingStove.class, "cookingStove");
    }
}
