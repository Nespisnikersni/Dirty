package nespisnikersni.dirty.compat.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.Comparison;
import dev.emi.emi.api.stack.EmiStack;
import nespisnikersni.dirty.Dirty;
import nespisnikersni.dirty.blocks.DirtyBlocks;
import nespisnikersni.dirty.items.DirtyItems;
import nespisnikersni.dirty.recipes.DirtRecyclerRecipe;
import nespisnikersni.dirty.recipes.types.DirtRecyclerRecipeType;
import nespisnikersni.dirty.screen.DirtRecyclerScreen;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;

public class DirtyEMIPlugin implements EmiPlugin {
    public static final EmiStack CATEGORY_ITEM = EmiStack.of(DirtyBlocks.DIRT_RECYCLER_ITEM);
    public static final EmiRecipeCategory DIRTY_CATEGORY = new EmiRecipeCategory(new Identifier(Dirty.MOD_ID,"main"),CATEGORY_ITEM,new EmiTexture(DirtRecyclerScreen.TEXTURE,0,0,16,16));
    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(DIRTY_CATEGORY);
        registry.addWorkstation(DIRTY_CATEGORY,CATEGORY_ITEM);
        RecipeManager manager = registry.getRecipeManager();
        for (DirtRecyclerRecipe recipe : manager.listAllOfType(DirtRecyclerRecipeType.INSTANCE)) {
            registry.addRecipe(new DirtRecyclerEmiRecipe(recipe));
        }
    }
}
