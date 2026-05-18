package com.loenaaaa.chinesecooking.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCookingMachineStandard extends BlockContainer {

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
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null; // TODO: Once TileEntities for inheritors are finished, this method should be removed and the class
                     // should be made abstract.
    }
}
