package nespisnikersni.dirty.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import nespisnikersni.dirty.items.DirtyItems;
import nespisnikersni.dirty.recipes.DirtRecyclerRecipe;
import nespisnikersni.dirty.recipes.ProcessingTableRecipe;
import nespisnikersni.dirty.util.iterator.SequentialItemProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

import static nespisnikersni.dirty.screen.DirtRecyclerScreen.TEXTURE;

public class ProcessingTableEmiRecipe implements EmiRecipe {
    private final Identifier id;
    private final List<EmiIngredient> input;
    private final List<EmiStack> output;

    public ProcessingTableEmiRecipe(ProcessingTableRecipe recyclerRecipe) {
        this.id = recyclerRecipe.getId();
        this.input = List.of(EmiIngredient.of(recyclerRecipe.getIngredient()));
        this.output = List.of(EmiStack.of(recyclerRecipe.getOutput()));
    }


    @Override
    public EmiRecipeCategory getCategory() {
        return DirtyEMIPlugin.PROCESSING_TABLE_CATEGORY;
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
        SequentialItemProvider sequentialItemProvider = new SequentialItemProvider(Arrays.asList(DirtyItems.WOODEN_HAMMER,DirtyItems.STONE_HAMMER,DirtyItems.IRON_HAMMER,
                DirtyItems.GOLDEN_HAMMER,DirtyItems.DIAMOND_HAMMER,DirtyItems.NETHERITE_HAMMER));
        widgets.addSlot(input.get(0),80,11);
        widgets.addSlot(output.get(0),80,59).recipeContext(this);
        widgets.addTexture(TEXTURE,85,30,8,26,176,0);
        widgets.addGeneratedSlot(random -> EmiIngredient.of(sequentialItemProvider.getNextItem()), 0, 52, 32);

    }
}
