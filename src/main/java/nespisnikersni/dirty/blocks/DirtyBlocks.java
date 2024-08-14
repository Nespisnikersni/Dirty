package nespisnikersni.dirty.blocks;

import nespisnikersni.dirty.Dirty;
import nespisnikersni.dirty.blocks.entities.dirt_recycler.DirtRecycler;
import nespisnikersni.dirty.blocks.entities.processing_table.ProcessingTable;
import nespisnikersni.dirty.blocks.entities.sieve.Sieve;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DirtyBlocks {
    public static final Block DIRT_BLOCK = registerBlock("dirt_block",new Block(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK)));
    public static final Block DIRT_RECYCLER = registerBlock("dirt_recycler", new DirtRecycler(FabricBlockSettings.copyOf(Blocks.DIRT)));
    public static final Block PROCESSING_TABLE = registerBlock("processing_table", new ProcessingTable(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block SIEVE = registerBlock("sieve", new Sieve(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block WHITE_DUST_ORE = registerBlock("white_dust_ore",new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE)));

    public static final Item WHITE_DUST_ORE_ITEM = registerBlockItem("white_dust_ore",WHITE_DUST_ORE);
    public static final Item DIRT_BLOCK_ITEM = registerBlockItem("dirt_block",DIRT_BLOCK);
    public static final Item DIRT_RECYCLER_ITEM = registerBlockItem("dirt_recycler",DIRT_RECYCLER);
    public static final Item PROCESSING_TABLE_ITEM = registerBlockItem("processing_table",PROCESSING_TABLE);
    public static final Item SIEVE_ITEM = registerBlockItem("sieve",SIEVE);

    private static Block registerBlock(String id, Block block){
        return Registry.register(Registries.BLOCK,new Identifier(Dirty.MOD_ID,id),block);
    }
    private static Item registerBlockItem(String id, Block block){
        return Registry.register(Registries.ITEM, new Identifier(Dirty.MOD_ID, id), new BlockItem(block,new FabricItemSettings()));
    }
    public static void register(){}
}
