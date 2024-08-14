package nespisnikersni.dirty.util.iterator;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;

import java.util.Iterator;
import java.util.List;

public class SequentialItemProvider {
    private final List<Item> items;
    private Iterator<Item> iterator;

    public SequentialItemProvider(List<Item> items) {
        this.items = items;
        this.iterator = items.iterator();
    }

    public Ingredient getNextItem() {
        if (!iterator.hasNext()) {
            resetIterator();
        }
        return Ingredient.ofItems(iterator.next());
    }

    private void resetIterator() {
        iterator = items.iterator();
    }
}





