package nespisnikersni.dirty.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import nespisnikersni.dirty.recipes.DirtRecyclerRecipe;
import nespisnikersni.dirty.util.iterator.SequentialItemProvider;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static nespisnikersni.dirty.screen.DirtRecyclerScreen.TEXTURE;

public class DirtRecyclerEmiRecipe implements EmiRecipe {
    private final Identifier id;
    private final List<EmiIngredient> input;
    private final List<EmiStack> output;

    public DirtRecyclerEmiRecipe(DirtRecyclerRecipe recyclerRecipe) {
        this.id = recyclerRecipe.getId();
        this.input = List.of(EmiIngredient.of(recyclerRecipe.getInput()));
        this.output = List.of(EmiStack.of(recyclerRecipe.getOutput()));
    }


    @Override
    public EmiRecipeCategory getCategory() {
        return DirtyEMIPlugin.DIRT_RECYCLER_CATEGORY;
    }

    @Override
    public @Nullable Identifier getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return input;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return output;
    }

    @Override
    public int getDisplayWidth() {
        return 176;
    }

    @Override
    public int getDisplayHeight() {
        return 100;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addSlot(input.get(0),80,11);
        widgets.addSlot(output.get(0),80,59).recipeContext(this);
        widgets.addTexture(TEXTURE,85,30,8,26,176,0);
        widgets.addTexture(TEXTURE,116,30,8,23,176,26);
        SequentialItemProvider sequentialItemProvider = new SequentialItemProvider(getAllFuelItems());
        widgets.addGeneratedSlot(random -> EmiIngredient.of(sequentialItemProvider.getNextItem()),0,52,32);
    }
    public static List<Item> getAllFuelItems() {
        List<Item> fuelItems = new ArrayList<>();
        FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;

        for (Item item : Registries.ITEM) {
            if (fuelRegistry.get(item) != null) {
                fuelItems.add(item);
            }
        }

        return fuelItems;
    }
}
