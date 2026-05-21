package com.loenaaaa.chinesecooking.common.tileentities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.api.widget.IWidget;
import com.cleanroommc.modularui.drawable.UITexture;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.utils.item.IItemHandler;
import com.cleanroommc.modularui.utils.item.InvWrapper;
import com.cleanroommc.modularui.utils.item.ItemStackHandler;
import com.cleanroommc.modularui.value.sync.FloatSyncValue;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.widgets.ProgressWidget;
import com.cleanroommc.modularui.widgets.layout.Flow;
import com.cleanroommc.modularui.widgets.layout.Grid;
import com.cleanroommc.modularui.widgets.slot.ItemSlot;
import com.cleanroommc.modularui.widgets.slot.ModularSlot;
import com.cleanroommc.modularui.widgets.slot.SlotGroup;
import com.loenaaaa.chinesecooking.ChineseCooking;
import com.loenaaaa.chinesecooking.common.recipe.CookingRecipe;

public abstract class TileEntityCookingMachineStandard extends TileEntity
    implements IGuiHolder<PosGuiData>, IInventory {

    private final String unlocalizedTitle;
    private final ItemStack[] ingredientStacks;
    private final ItemStack[] outputStacks;
    private final int ingredientSlotAmount;
    private final int outputSlotAmount;

    private boolean isRunningRecipe = false;
    private int recipeTotalTime = 0;
    private int recipeRemainingTime = 0;
    private int recipeIndexInList;

    public TileEntityCookingMachineStandard(String unlocalizedTitle, int ingredientSlotAmount, int outputSlotAmount) {
        this.unlocalizedTitle = "gui.tooltip." + unlocalizedTitle;
        this.ingredientSlotAmount = ingredientSlotAmount;
        this.outputSlotAmount = outputSlotAmount;
        this.ingredientStacks = new ItemStack[ingredientSlotAmount];
        this.outputStacks = new ItemStack[outputSlotAmount];
    }

    static {
        addMapping(TileEntityCookingMachineStandard.class, "cookingMachineStandard");
    }

    @Override
    public ModularPanel buildUI(PosGuiData data, PanelSyncManager syncManager, UISettings settings) {
        FloatSyncValue progressTimeSyncer = new FloatSyncValue(
            () -> ((float) recipeTotalTime - recipeRemainingTime) / 20);
        FloatSyncValue totalTimeSyncer = new FloatSyncValue(() -> (float) recipeTotalTime / 20);
        syncManager.syncValue("progressTimeSyncer", progressTimeSyncer);
        syncManager.syncValue("totalTimeSyncer", totalTimeSyncer);

        SlotGroup ingredientSlotGroup = new SlotGroup("ingredient_slots", 2);
        SlotGroup outputSlotGroup = new SlotGroup("output_slots", 1);

        IItemHandler ingredientItemHandler = new InvWrapper(this);
        IItemHandler outputItemHandler = new ItemStackHandler(outputStacks);

        ModularSlot[] ingredientSlots = new ModularSlot[ingredientSlotAmount];
        ModularSlot[] outputSlots = new ModularSlot[outputSlotAmount];

        for (int i = 0; i < ingredientSlotAmount; i++) {
            ingredientSlots[i] = new ModularSlot(ingredientItemHandler, i).slotGroup(ingredientSlotGroup);
        }

        for (int i = 0; i < outputSlotAmount; i++) {
            outputSlots[i] = new ModularSlot(outputItemHandler, i).slotGroup(outputSlotGroup)
                .accessibility(false, true);
        }

        return new ModularPanel("chinesecooking:test_tile").size(200, 200)
            .bindPlayerInventory()
            .child(
                IKey.lang(unlocalizedTitle)
                    .asWidget()
                    .top(6)
                    .leftRel(0.5F))
            .child(getRecipeInfoWidget(syncManager))
            .child(
                new Grid().coverChildren()
                    .pos(20, 20)
                    .mapTo(2, ingredientSlotAmount, index -> new ItemSlot().slot(ingredientSlots[index])))
            .child(
                new Grid().coverChildren()
                    .right(20)
                    .top(20)
                    .mapTo(1, outputSlotAmount, index -> new ItemSlot().slot(outputSlots[index])));
    }

    private IWidget getRecipeInfoWidget(PanelSyncManager syncManager) {
        FloatSyncValue progressTimeSyncer = syncManager.findSyncHandler("progressTimeSyncer", FloatSyncValue.class);
        FloatSyncValue totalTimeSyncer = syncManager.findSyncHandler("totalTimeSyncer", FloatSyncValue.class);
        final UITexture COOKING_PROGRESS = UITexture.builder()
            .location(ChineseCooking.MODID, "gui/progress_cooking")
            .imageSize(16, 24)
            .build();
        return Flow.column()
            .childPadding(2)
            .child(
                IKey.lang("gui.tooltip.recipe_time")
                    .asWidget())
            .child(
                new ProgressWidget()
                    .value(new FloatSyncValue(() -> (float) progressTimeSyncer.getValue() / totalTimeSyncer.getValue()))
                    .texture(COOKING_PROGRESS, 16)
                    .direction(ProgressWidget.Direction.UP)
                    .size(24, 24))
            .child(
                IKey.dynamic(
                    () -> String.format("%.1f", progressTimeSyncer.getValue()) + "s / "
                        + String.format("%.1f", totalTimeSyncer.getValue())
                        + "s")
                    .asWidget())
            .top(35)
            .leftRel(0.5F);
    }

    @Override
    public int getSizeInventory() {
        return ingredientSlotAmount;
    }

    @Override
    public ItemStack getStackInSlot(int slotIn) {
        return ingredientStacks[slotIn];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (ingredientStacks[index] == null) return null;
        ItemStack extractedStack;
        if (ingredientStacks[index].stackSize <= count) {
            extractedStack = ingredientStacks[index];
            setInventorySlotContents(index, null);
        } else {
            extractedStack = ingredientStacks[index].splitStack(count);
            setInventorySlotContents(index, ingredientStacks[index].stackSize <= 0 ? null : ingredientStacks[index]);
        }
        return extractedStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        return null;
    }

    protected boolean checkRecipeAndConsume(CookingRecipe cookingRecipe) {
        boolean requiredIngredientFound;
        Map<Integer, Integer> stacksToDecrease = new HashMap<>();
        for (ItemStack requiredIngredient : cookingRecipe.inputItems) {
            requiredIngredientFound = false;
            for (int slotIndex = 0; slotIndex < ingredientSlotAmount; slotIndex++) {
                ItemStack storedIngredient = ingredientStacks[slotIndex];
                if (storedIngredient == null || storedIngredient.stackSize <= 0) continue;
                if (storedIngredient.getItem() != requiredIngredient.getItem()
                    || storedIngredient.getItemDamage() != requiredIngredient.getItemDamage()) continue;
                if (storedIngredient.stackSize < requiredIngredient.stackSize) continue;
                requiredIngredientFound = true;
                stacksToDecrease.put(slotIndex, requiredIngredient.stackSize);
                break;
            }
            if (!requiredIngredientFound) {
                return false;
            }
        }
        if (!outputItems(cookingRecipe.outputItems.toArray(new ItemStack[0]), true)) return false;
        if (!additionalRecipeChecks(cookingRecipe)) return false;
        isRunningRecipe = true;
        for (int i = 0; i < ingredientSlotAmount; i++) {
            if (!stacksToDecrease.containsKey(i)) continue;
            decrStackSize(i, stacksToDecrease.get(i));
        }
        return true;
    }

    private boolean additionalRecipeChecks(CookingRecipe cookingRecipe) {
        return true;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ingredientStacks[index] = stack;
        if (worldObj.isRemote) return;
        markDirty();
        checkForRecipes();
    }

    public abstract ArrayList<CookingRecipe> getRecipeList();

    private void checkForRecipes() {
        if (isRunningRecipe) return;
        if (getRecipeList() == null) return;
        for (int i = 0; i < getRecipeList().size(); i++) {
            CookingRecipe cookingRecipe = getRecipeList().get(i);
            if (!checkRecipeAndConsume(cookingRecipe)) return;
            recipeIndexInList = i;
            recipeRemainingTime = cookingRecipe.recipeTime;
            recipeTotalTime = cookingRecipe.recipeTime;
        }
    }

    @Override
    public String getInventoryName() {
        return "tile." + unlocalizedTitle + ".name";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        for (int i = 0; i < ingredientSlotAmount; i++) {
            setInventorySlotContents(
                i,
                ItemStack.loadItemStackFromNBT(compound.getCompoundTag("ingredientStacks" + i)));
        }
        for (int i = 0; i < outputSlotAmount; i++) {
            outputStacks[i] = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("outputStacks" + i));
        }
        isRunningRecipe = compound.getBoolean("isRunningRecipe");
        recipeRemainingTime = compound.getInteger("recipeRemainingTime");
        recipeTotalTime = compound.getInteger("recipeTotalTime");
        recipeIndexInList = compound.getInteger("recipeIndexInList");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        for (int i = 0; i < ingredientSlotAmount; i++) {
            final ItemStack ingredientStack = ingredientStacks[i];
            if (ingredientStack != null) {
                NBTTagCompound ingredientStackTag = new NBTTagCompound();
                ingredientStack.writeToNBT(ingredientStackTag);
                compound.setTag("ingredientStacks" + i, ingredientStackTag);
            }
        }
        for (int i = 0; i < outputSlotAmount; i++) {
            final ItemStack outputStack = outputStacks[i];
            if (outputStack != null) {
                NBTTagCompound outputStackTag = new NBTTagCompound();
                outputStack.writeToNBT(outputStackTag);
                compound.setTag("outputStacks" + i, outputStackTag);
            }
        }
        compound.setBoolean("isRunningRecipe", isRunningRecipe);
        compound.setInteger("recipeRemainingTime", recipeRemainingTime);
        compound.setInteger("recipeTotalTime", recipeTotalTime);
        compound.setInteger("recipeIndexInList", recipeIndexInList);
    }

    private boolean outputItems(ItemStack[] stacksToOutput, boolean isTest) {
        Map<Integer, ItemStack> stacksToIncrease = new HashMap<>();
        for (ItemStack itemToOutput : stacksToOutput) {
            boolean canOutput = false;
            if (itemToOutput == null || itemToOutput.stackSize <= 0) {
                continue;
            }
            for (int i = 0; i < outputSlotAmount; i++) {
                if (stacksToIncrease.containsKey(i)) continue;
                ItemStack outputStack = outputStacks[i];
                int remainingSize = 64 - itemToOutput.stackSize;
                if (remainingSize < 0) continue;
                if (outputStack == null) {
                    canOutput = true;
                    stacksToIncrease.put(i, itemToOutput);
                    break;
                }
                if (itemToOutput.getItem() != outputStack.getItem()
                    || itemToOutput.getItemDamage() != outputStack.getItemDamage()) {
                    continue;
                }
                remainingSize -= outputStack.stackSize;
                if (remainingSize < 0) continue;
                canOutput = true;
                stacksToIncrease.put(i, itemToOutput);
                break;
            }
            if (!canOutput) return false;
        }
        if (isTest) return true;
        for (int i = 0; i < outputSlotAmount; i++) {
            if (!stacksToIncrease.containsKey(i)) continue;
            if (outputStacks[i] == null) {
                outputStacks[i] = stacksToIncrease.get(i);
            } else {
                outputStacks[i].stackSize = Math.min(64, outputStacks[i].stackSize + stacksToIncrease.get(i).stackSize);
            }
        }
        return true;
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;
        if (!isRunningRecipe) return;
        recipeRemainingTime--;
        markDirty();
        if (recipeRemainingTime > 0) return;
        isRunningRecipe = false;
        recipeRemainingTime = 0;
        recipeTotalTime = 0;
        outputItems(getRecipeList().get(recipeIndexInList).outputItems.toArray(new ItemStack[0]), false);
        checkForRecipes();
    }
}
