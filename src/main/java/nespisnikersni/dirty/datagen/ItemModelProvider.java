package nespisnikersni.dirty.datagen;

import nespisnikersni.dirty.blocks.DirtyBlocks;
import nespisnikersni.dirty.items.DirtyItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.DataOutput;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ItemModelProvider extends FabricModelProvider {
    public ItemModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(DirtyBlocks.WHITE_DUST_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(DirtyItems.WHITE_ROOTS_BOOTS,Models.GENERATED);
        itemModelGenerator.register(DirtyItems.WHITE_ROOTS_LEGGINGS,Models.GENERATED);
        itemModelGenerator.register(DirtyItems.WHITE_ROOTS_CHESTPLATE,Models.GENERATED);
        itemModelGenerator.register(DirtyItems.WHITE_ROOTS_HELMET,Models.GENERATED);
        itemModelGenerator.register(DirtyItems.WHITE_DUST,Models.GENERATED);
        itemModelGenerator.register(DirtyItems.WHITE_CRISTAL,Models.GENERATED);
    }


}