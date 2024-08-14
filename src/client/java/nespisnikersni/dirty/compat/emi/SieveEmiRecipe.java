package nespisnikersni.dirty.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import nespisnikersni.dirty.recipes.SieveRepice;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static nespisnikersni.dirty.screen.DirtRecyclerScreen.TEXTURE;

public class SieveEmiRecipe implements EmiRecipe {
    private final Identifier id;
    private final EmiStack output;
    private final EmiIngredient ingredient;
    public SieveEmiRecipe(SieveRepice repice){
        id = repice.getId();
        ingredient = EmiIngredient.of(repice.getIngredient());
        output = EmiStack.of(repice.getOutput());
    }
    @Override
    public EmiRecipeCategory getCategory() {
        return DirtyEMIPlugin.SIEVE_CATEGORY;
    }

    @Override
    public @Nullable Identifier getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return Collections.singletonList(ingredient);
    }

    @Override
    public List<EmiStack> getOutputs() {
        return Collections.singletonList(output);
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
        widgets.addSlot(ingredient,80,11);
        widgets.addSlot(output,80,59).recipeContext(this);
        widgets.addTexture(TEXTURE,85,30,8,26,176,0);
    }
}
