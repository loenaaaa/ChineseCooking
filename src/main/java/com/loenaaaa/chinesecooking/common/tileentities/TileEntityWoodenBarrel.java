package com.loenaaaa.chinesecooking.common.tileentities;

import java.util.ArrayList;

import com.loenaaaa.chinesecooking.common.recipe.CookingRecipe;

public class TileEntityWoodenBarrel extends TileEntityCookingMachineStandard {

    public TileEntityWoodenBarrel() {
        super("barrel", 6, 1);
    }

    @Override
    public ArrayList<CookingRecipe> getRecipeList() {
        return CookingRecipe.CookingRecipeLists.fermenterRecipes;
    }

    static {
        addMapping(TileEntityWoodenBarrel.class, "woodenBarrel");
    }
}
