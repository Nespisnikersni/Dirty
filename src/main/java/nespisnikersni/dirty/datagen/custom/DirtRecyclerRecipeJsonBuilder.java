package nespisnikersni.dirty.datagen.custom;

import com.google.gson.JsonObject;
import nespisnikersni.dirty.recipes.DirtRecyclerRecipe;
import nespisnikersni.dirty.recipes.types.DirtRecyclerRecipeType;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class DirtRecyclerRecipeJsonBuilder {
    private final Identifier recipeId;
    private final Item outputItem;
    private final int outputCount;
    private Ingredient input;

    private DirtRecyclerRecipeJsonBuilder(Item outputItem, int outputCount) {
        this.outputItem = outputItem;
        this.outputCount = outputCount;
        this.recipeId = Registries.ITEM.getId(outputItem);
    }

    public static DirtRecyclerRecipeJsonBuilder create(Item outputItem, int outputCount) {
        return new DirtRecyclerRecipeJsonBuilder(outputItem, outputCount);
    }

    public DirtRecyclerRecipeJsonBuilder addInput(Item inputItem) {
        this.input = Ingredient.ofItems(inputItem);
        return this;
    }

    public DirtRecyclerRecipeJsonBuilder addInput(TagKey<Item> inputTag) {
        this.input = Ingredient.fromTag(inputTag);
        return this;
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter) {
        exporter.accept(new RecipeJsonProvider() {
            @Override
            public void serialize(JsonObject json) {

                json.addProperty("type", DirtRecyclerRecipeType.ID.toString());
                json.add("ingredient", input.toJson());

                JsonObject result = new JsonObject();
                result.addProperty("item", Registries.ITEM.getId(outputItem).toString());
                result.addProperty("count",outputCount);
                json.add("result", result);
            }

            @Override
            public Identifier getRecipeId() {
                return recipeId;
            }

            @Override
            public RecipeSerializer<?> getSerializer() {
                return DirtRecyclerRecipe.DirtRecyclerRecipeSerializer.INSTANCE;
            }

            @Nullable
            @Override
            public JsonObject toAdvancementJson() {
                return null;
            }

            @Nullable
            @Override
            public Identifier getAdvancementId() {
                return null;
            }

            @Override
            public JsonObject toJson() {
                JsonObject json = new JsonObject();
                serialize(json);
                return json;
            }
        });
    }
}

