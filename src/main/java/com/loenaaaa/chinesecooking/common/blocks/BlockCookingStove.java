package com.loenaaaa.chinesecooking.common.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.loenaaaa.chinesecooking.common.tileentities.TileEntityCookingStove;

public class BlockCookingStove extends BlockCookingMachineStandard {

    public BlockCookingStove() {
        super("stove", true);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCookingStove();
    }
}
