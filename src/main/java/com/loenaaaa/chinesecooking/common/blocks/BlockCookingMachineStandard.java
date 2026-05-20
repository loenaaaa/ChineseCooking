package com.loenaaaa.chinesecooking.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.cleanroommc.modularui.factory.GuiFactories;

public abstract class BlockCookingMachineStandard extends BlockContainer {

    private String standardizedName;

    /**
     * If this is true, the front face will have a unique texture compared to the other three sides.
     */
    private final boolean hasFrontTexture;
    private IIcon iconFront;
    private IIcon iconTop;
    private IIcon iconBottom;

    public BlockCookingMachineStandard(String standardizedName, boolean hasFront) {
        super(Material.iron);
        this.setBlockName(standardizedName);
        this.setBlockTextureName("chinesecooking:" + standardizedName);
        this.hasFrontTexture = hasFront;
        this.standardizedName = standardizedName;
    }

    public boolean isHasFrontTexture() {
        return hasFrontTexture;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return (side == 0) ? iconBottom : ((side == 1) ? iconTop : (side == 2) ? iconFront : blockIcon);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        iconTop = reg.registerIcon(this.getTextureName() + "_top");
        iconBottom = reg.registerIcon(this.getTextureName() + "_bottom");
        blockIcon = reg.registerIcon(this.getTextureName() + "_side");
        iconFront = hasFrontTexture ? reg.registerIcon(this.getTextureName() + "_front") : blockIcon;
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer playerIn, int side, float hitX,
        float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            GuiFactories.tileEntity()
                .open(playerIn, x, y, z);
        }
        return true;
    }
}
