package nespisnikersni.dirty.datagen;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import nespisnikersni.dirty.blocks.DirtyBlocks;
import nespisnikersni.dirty.items.DirtyItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.LootTable;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class DirtyBlockLootTableProvider extends FabricBlockLootTableProvider {


    public DirtyBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(DirtyBlocks.WHITE_DUST_ORE,oreDrops(DirtyBlocks.WHITE_DUST_ORE, DirtyItems.WHITE_DUST));
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> exporter) {
        super.accept(exporter);
    }
}
