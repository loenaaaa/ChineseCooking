package com.loenaaaa.chinesecooking.common.tileentities;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.StatCollector;

import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.api.widget.IWidget;
import com.cleanroommc.modularui.drawable.UITexture;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.utils.item.IItemHandler;
import com.cleanroommc.modularui.utils.item.ItemStackHandler;
import com.cleanroommc.modularui.value.sync.FloatSyncValue;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.widgets.ProgressWidget;
import com.cleanroommc.modularui.widgets.layout.Flow;
import com.cleanroommc.modularui.widgets.slot.ItemSlot;
import com.cleanroommc.modularui.widgets.slot.ModularSlot;
import com.loenaaaa.chinesecooking.ChineseCooking;
import com.loenaaaa.chinesecooking.common.recipe.CookingRecipe;

public class TileEntityCookingStove extends TileEntityCookingMachineStandard {

    private int heat = 2000;
    public static final int MIN_HEAT = 2000;
    public static final int MAX_HEAT = 20000;
    private int fuelHeatIncrease;
    private int fuelRemainingTime;

    private ItemStack[] fuelStack = new ItemStack[1];

    public static final UITexture HEAT_PROGRESS = UITexture.builder()
        .location(ChineseCooking.MODID, "gui/progress_heat")
        .imageSize(32, 16)
        .build();

    public TileEntityCookingStove() {
        super("stove", 8, 1);
    }

    @Override
    public ArrayList<CookingRecipe> getRecipeList() {
        return CookingRecipe.CookingRecipeLists.fryingRecipes;
        // TODO: change recipe list to be dynamic
        // based on tool used
    }

    @Override
    protected boolean additionalRecipeChecks(CookingRecipe cookingRecipe) {
        return heat >= cookingRecipe.minTemp && heat <= cookingRecipe.maxTemp;
    }

    @Override
    public ModularPanel buildUI(PosGuiData data, PanelSyncManager syncManager, UISettings settings) {
        FloatSyncValue heatSyncer = new FloatSyncValue(() -> (float) heat / 100);
        syncManager.syncValue("heatSyncer", heatSyncer);
        ModularPanel panel = super.buildUI(data, syncManager, settings);
        panel.child(getHeatInfoField(syncManager));
        return panel;
    }

    private IWidget getHeatInfoField(PanelSyncManager syncManager) {
        IItemHandler fuelStackHandler = new ItemStackHandler(fuelStack) {

            @Override
            protected void onContentsChanged(int slot) {
                consumeFuel();
            }
        };
        ModularSlot fuelStackSlot = new ModularSlot(fuelStackHandler, 0);
        FloatSyncValue heatSyncer = syncManager.findSyncHandler("heatSyncer", FloatSyncValue.class);
        return Flow.row()
            .bottom(90)
            .left(20)
            .height(10)
            .childPadding(5)
            .child(new ItemSlot().slot(fuelStackSlot))
            .child(
                new ProgressWidget().value(new FloatSyncValue(() -> (heatSyncer.getValue() - 20) / 180))
                    .texture(HEAT_PROGRESS, 32)
                    .direction(ProgressWidget.Direction.RIGHT)
                    .size(32, 8))
            .child(
                IKey.dynamic(
                    () -> StatCollector.translateToLocal("gui.tooltip.heat") + " "
                        + String.format("%.1f", heatSyncer.getValue())
                        + "ºC")
                    .asWidget());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        heat = compound.getInteger("heat");
        fuelStack[0] = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("fuelStack"));
        fuelHeatIncrease = compound.getInteger("fuelHeatIncrease");
        fuelRemainingTime = compound.getInteger("fuelRemainingTime");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("heat", heat);
        NBTTagCompound fuelStackTag = new NBTTagCompound();
        fuelStack[0].writeToNBT(fuelStackTag);
        compound.setTag("fuelStack", fuelStackTag);
        compound.setInteger("fuelHeatIncrease", fuelHeatIncrease);
        compound.setInteger("fuelRemainingTime", fuelRemainingTime);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        heat = (299 * heat + MIN_HEAT) / 300;
        if (fuelHeatIncrease <= 0) return;
        heat += fuelHeatIncrease;
        clampHeat();
        fuelRemainingTime--;
        if (fuelRemainingTime > 0) return;
        consumeFuel();
    }

    private void consumeFuel() {
        if (fuelStack[0] == null || fuelStack[0].stackSize <= 0) {
            fuelHeatIncrease = 0;
            return;
        }
        final int burnTime = TileEntityFurnace.getItemBurnTime(fuelStack[0]);
        if (burnTime <= 0) {
            fuelHeatIncrease = 0;
            return;
        }
        fuelStack[0].stackSize--;
        if (fuelStack[0].stackSize == 0) fuelStack[0] = null;
        fuelRemainingTime = burnTime / 6;
        fuelHeatIncrease = (int) (25 * Math.pow(burnTime, 0.2D));
    }

    private void clampHeat() {
        heat = Math.min(Math.max(heat, MIN_HEAT), MAX_HEAT);
    }

    static {
        addMapping(TileEntityCookingStove.class, "cookingStove");
    }
}
