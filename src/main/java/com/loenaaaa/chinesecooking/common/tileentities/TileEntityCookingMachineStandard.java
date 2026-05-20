package com.loenaaaa.chinesecooking.common.tileentities;

import net.minecraft.tileentity.TileEntity;

import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;

public class TileEntityCookingMachineStandard extends TileEntity implements IGuiHolder<PosGuiData> {

    private final String unlocalizedTitle;

    public TileEntityCookingMachineStandard(String unlocalizedTitle) {
        this.unlocalizedTitle = "gui.tooltip." + unlocalizedTitle;
    }

    @Override
    public ModularPanel buildUI(PosGuiData data, PanelSyncManager syncManager, UISettings settings) {
        return new ModularPanel("chinesecooking:test_tile").child(
            IKey.lang(unlocalizedTitle)
                .asWidget());
    }
}
