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
import nespisnikersni.dirty.recipes.ProcessingTableRecipe;
import nespisnikersni.dirty.recipes.SieveRepice;
import nespisnikersni.dirty.recipes.types.DirtRecyclerRecipeType;
import nespisnikersni.dirty.recipes.types.ProcessingTableRecipeType;
import nespisnikersni.dirty.recipes.types.SieveRecipeType;
import nespisnikersni.dirty.screen.DirtRecyclerScreen;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;

public class DirtyEMIPlugin implements EmiPlugin {
    public static final EmiStack DIRT_RECYCLER_CATEGORY_ITEM = EmiStack.of(DirtyBlocks.DIRT_RECYCLER_ITEM);
    public static final EmiStack PROCESSING_TABLE_CATEGORY_ITEM = EmiStack.of(DirtyBlocks.PROCESSING_TABLE_ITEM);
    public static final EmiStack SIEVE_CATEGORY_ITEM = EmiStack.of(DirtyBlocks.SIEVE_ITEM);
    public static final EmiRecipeCategory DIRT_RECYCLER_CATEGORY = new EmiRecipeCategory(new Identifier(Dirty.MOD_ID,"recycling"),DIRT_RECYCLER_CATEGORY_ITEM,
            new EmiTexture(DirtRecyclerScreen.TEXTURE,0,0,16,16));
    public static final EmiRecipeCategory PROCESSING_TABLE_CATEGORY = new EmiRecipeCategory(new Identifier(Dirty.MOD_ID,"processing"),PROCESSING_TABLE_CATEGORY_ITEM,
            new EmiTexture(DirtRecyclerScreen.TEXTURE,0,0,16,16));
    public static final EmiRecipeCategory SIEVE_CATEGORY = new EmiRecipeCategory(new Identifier(Dirty.MOD_ID,"screening"),SIEVE_CATEGORY_ITEM,
            new EmiTexture(DirtRecyclerScreen.TEXTURE,0,0,16,16));
    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(DIRT_RECYCLER_CATEGORY);
        registry.addWorkstation(DIRT_RECYCLER_CATEGORY,DIRT_RECYCLER_CATEGORY_ITEM);
        RecipeManager manager = registry.getRecipeManager();
        for (DirtRecyclerRecipe recipe : manager.listAllOfType(DirtRecyclerRecipeType.INSTANCE)) {
            registry.addRecipe(new DirtRecyclerEmiRecipe(recipe));
        }
        registry.addCategory(PROCESSING_TABLE_CATEGORY);
        registry.addWorkstation(PROCESSING_TABLE_CATEGORY,PROCESSING_TABLE_CATEGORY_ITEM);
        RecipeManager manager2 = registry.getRecipeManager();
        for (ProcessingTableRecipe recipe : manager2.listAllOfType(ProcessingTableRecipeType.INSTANCE)) {
            registry.addRecipe(new ProcessingTableEmiRecipe(recipe));
        }
        registry.addCategory(SIEVE_CATEGORY);
        registry.addWorkstation(SIEVE_CATEGORY,SIEVE_CATEGORY_ITEM);
        RecipeManager manager3 = registry.getRecipeManager();
        for (SieveRepice recipe : manager3.listAllOfType(SieveRecipeType.INSTANCE)) {
            registry.addRecipe(new SieveEmiRecipe(recipe));
        }
    }
}
