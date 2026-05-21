package com.loenaaaa.chinesecooking.common.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.loenaaaa.chinesecooking.common.tileentities.TileEntityWoodenBarrel;

public class BlockWoodenBarrel extends BlockCookingMachineStandard {

    public BlockWoodenBarrel() {
        super("barrel", false);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityWoodenBarrel();
    }
}
