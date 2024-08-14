package nespisnikersni.dirty.datagen;

import nespisnikersni.dirty.Dirty;
import nespisnikersni.dirty.datagen.custom.ProcessingTableRecipeJsonBuilder;
import nespisnikersni.dirty.items.DirtyItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class DirtyRecipeProvider extends FabricRecipeProvider {
    public DirtyRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,DirtyItems.WHITE_CRISTAL)
                .pattern("DD")
                .pattern("DD")
                .input('D', DirtyItems.WHITE_DUST)
                .criterion(FabricRecipeProvider.hasItem(DirtyItems.WHITE_DUST),
                        FabricRecipeProvider.conditionsFromItem(DirtyItems.WHITE_DUST))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT,DirtyItems.WHITE_ROOTS_HELMET)
                .pattern("DDD")
                .pattern("D D")
                .input('D', DirtyItems.WHITE_ROOTS)
                .criterion(FabricRecipeProvider.hasItem(DirtyItems.WHITE_ROOTS),
                        FabricRecipeProvider.conditionsFromItem(DirtyItems.WHITE_ROOTS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT,DirtyItems.WHITE_ROOTS_BOOTS)
                .pattern("D D")
                .pattern("D D")
                .input('D', DirtyItems.WHITE_ROOTS)
                .criterion(FabricRecipeProvider.hasItem(DirtyItems.WHITE_ROOTS),
                        FabricRecipeProvider.conditionsFromItem(DirtyItems.WHITE_ROOTS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT,DirtyItems.WHITE_ROOTS_CHESTPLATE)
                .pattern("D D")
                .pattern("DDD")
                .pattern("DDD")
                .input('D', DirtyItems.WHITE_ROOTS)
                .criterion(FabricRecipeProvider.hasItem(DirtyItems.WHITE_ROOTS),
                        FabricRecipeProvider.conditionsFromItem(DirtyItems.WHITE_ROOTS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT,DirtyItems.WHITE_ROOTS_LEGGINGS)
                .pattern("DDD")
                .pattern("D D")
                .pattern("D D")
                .input('D', DirtyItems.WHITE_ROOTS)
                .criterion(FabricRecipeProvider.hasItem(DirtyItems.WHITE_ROOTS),
                        FabricRecipeProvider.conditionsFromItem(DirtyItems.WHITE_ROOTS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT,DirtyItems.WHITE_ROOTS_PICKAXE)
                .pattern("DDD")
                .pattern(" S ")
                .pattern(" S ")
                .input('D', DirtyItems.WHITE_ROOTS)
                .input('S', Items.DIAMOND)
                .criterion(FabricRecipeProvider.hasItem(DirtyItems.WHITE_ROOTS),
                        FabricRecipeProvider.conditionsFromItem(DirtyItems.WHITE_ROOTS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT,DirtyItems.WHITE_ROOTS_AXE)
                .pattern("DD ")
                .pattern("DS ")
                .pattern(" S ")
                .input('D', DirtyItems.WHITE_ROOTS)
                .input('S', Items.DIAMOND)
                .criterion(FabricRecipeProvider.hasItem(DirtyItems.WHITE_ROOTS),
                        FabricRecipeProvider.conditionsFromItem(DirtyItems.WHITE_ROOTS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT,DirtyItems.WHITE_ROOTS_AXE)
                .pattern(" DD")
                .pattern(" SD")
                .pattern(" S ")
                .input('D', DirtyItems.WHITE_ROOTS)
                .input('S', Items.DIAMOND)
                .criterion(FabricRecipeProvider.hasItem(DirtyItems.WHITE_ROOTS),
                        FabricRecipeProvider.conditionsFromItem(DirtyItems.WHITE_ROOTS))
                .offerTo(exporter,new Identifier(Dirty.MOD_ID,"white_roots_axe2"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT,DirtyItems.WHITE_ROOTS_SWORD)
                .pattern("D")
                .pattern("D")
                .pattern("S")
                .input('D', DirtyItems.WHITE_ROOTS)
                .input('S', Items.DIAMOND)
                .criterion(FabricRecipeProvider.hasItem(DirtyItems.WHITE_ROOTS),
                        FabricRecipeProvider.conditionsFromItem(DirtyItems.WHITE_ROOTS))
                .offerTo(exporter);
        ProcessingTableRecipeJsonBuilder.create(DirtyItems.WHITE_ROOTS,1)
                .addInput(DirtyItems.WHITE_CRISTAL)
                .offerTo(exporter);
    }
}
