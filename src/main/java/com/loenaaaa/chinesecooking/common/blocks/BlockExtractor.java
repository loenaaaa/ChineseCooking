package com.loenaaaa.chinesecooking.common.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.loenaaaa.chinesecooking.common.recipe.CookingRecipe;
import com.loenaaaa.chinesecooking.common.tileentities.TileEntityCookingMachineStandard;

public class BlockExtractor extends BlockCookingMachineStandard {

    public BlockExtractor() {
        super("extractor", false);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCookingMachineStandard(
            "extractor",
            1,
            4,
            CookingRecipe.CookingRecipeLists.extractorRecipes);
    }
}
