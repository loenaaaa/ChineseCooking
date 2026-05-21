package com.loenaaaa.chinesecooking.common.tileentities;

import com.loenaaaa.chinesecooking.common.recipe.CookingRecipe;

public class TileEntityWoodenBarrel extends TileEntityCookingMachineStandard {

    public TileEntityWoodenBarrel() {
        super("barrel", 6, 1, CookingRecipe.CookingRecipeLists.fermenterRecipes);
    }

    static {
        addMapping(TileEntityWoodenBarrel.class, "woodenBarrel");
    }
}
