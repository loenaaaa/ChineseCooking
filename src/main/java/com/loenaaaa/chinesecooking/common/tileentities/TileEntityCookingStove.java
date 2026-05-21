package com.loenaaaa.chinesecooking.common.tileentities;

import java.util.ArrayList;

import com.loenaaaa.chinesecooking.common.recipe.CookingRecipe;

public class TileEntityCookingStove extends TileEntityCookingMachineStandard {

    private int heat;

    public TileEntityCookingStove() {
        super("stove", 8, 1);
    }

    @Override
    public ArrayList<CookingRecipe> getRecipeList() {
        return CookingRecipe.CookingRecipeLists.fryingRecipes;
        // TODO: change recipe list to be dynamic
        // based on tool used
    }

    static {
        addMapping(TileEntityCookingStove.class, "cookingStove");
    }
}
