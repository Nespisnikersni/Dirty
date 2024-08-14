package nespisnikersni.dirty.items;

import nespisnikersni.dirty.Dirty;
import nespisnikersni.dirty.blocks.DirtyBlocks;
import nespisnikersni.dirty.entities.ModEntities;
import nespisnikersni.dirty.materials.DirtyArmorMaterials;
import nespisnikersni.dirty.materials.DirtyToolMaterials;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class DirtyItems{
    public static final Item PIECE_OF_DIRT = regItem("piece_of_dirt",new Piece_of_dirt(new Item.Settings().maxCount(16)));
    public static final Item EXPLOSIVE_DIRT = regItem("explosive_dirt",new Explosive_dirt(new Item.Settings().maxCount(16)));
    public static final Item WHITE_ROOTS = regItem("white_roots",new Item(new Item.Settings()));
    public static final Item DRIED_ROOTS = regItem("dried_roots",new Item(new Item.Settings()));
    public static final Item LIVING_ROOTS = regItem("living_roots",new Item(new Item.Settings()));
    public static final Item MELTED_DIRT = regItem("melted_dirt",new Item(new Item.Settings()));
    public static final Item PILE_OF_DIRT = regItem("pile_of_dirt",new Item(new Item.Settings()));
    public static final Item BIOFUEL = regItem("biofuel",new Item(new Item.Settings()));
    public static final Item DIAMOND_HAMMER = regItem("diamond_hammer",new HammerItem(ToolMaterials.DIAMOND, 7,-2.4f,new Item.Settings()));
    public static final Item IRON_HAMMER = regItem("iron_hammer",new HammerItem(ToolMaterials.IRON, 6,-2.4f,new Item.Settings()));
    public static final Item GOLDEN_HAMMER = regItem("golden_hammer",new HammerItem(ToolMaterials.GOLD, 5,-2.4f,new Item.Settings()));
    public static final Item STONE_HAMMER = regItem("stone_hammer",new HammerItem(ToolMaterials.STONE, 5,-2.4f,new Item.Settings()));
    public static final Item WOODEN_HAMMER = regItem("wooden_hammer",new HammerItem(ToolMaterials.WOOD, 4,-2.4f,new Item.Settings()));
    public static final Item NETHERITE_HAMMER = regItem("netherite_hammer",new HammerItem(ToolMaterials.NETHERITE, 8,-2.4f,new Item.Settings()));
    public static final Item IRON_SHEET = regItem("iron_sheet",new Item(new Item.Settings()));
    public static final Item BURNT_IRON_SHEET = regItem("burnt_iron_sheet",new BurntIronSheet(new Item.Settings()));
    public static final Item LOOT_BOX = regItem("loot_box",new LootBoxItem(new Item.Settings()));
    public static final Item RANDOM_POTION = regItem("random_potion",new RandomPotion(new Item.Settings()));
    public static final Item MUD_SPAWN_EGG = regItem("mud_dog_spawn_egg",new SpawnEggItem(ModEntities.MUD_ENTITY_TYPE,Integer.parseInt("0E9925",16),Integer.parseInt("0E8622",16),new Item.Settings()));
    public static final Item DRIED_ROOTS_STAFF = regItem("dried_roots_staff",new Dried_roots_staff(DirtyToolMaterials.DRIED_ROOTS,7,-2.8f,new Item.Settings()));
    public static final Item DRIED_ROOTS_SWORD = regItem("dried_roots_sword",new Dried_roots_sword(DirtyToolMaterials.DRIED_ROOTS,8,-2.4f,new Item.Settings()));
    public static final Item DRIED_ROOTS_PICKAXE = regItem("dried_roots_pickaxe",new Dried_roots_pickaxe(DirtyToolMaterials.DRIED_ROOTS,6,-2.8f,new Item.Settings()));
    public static final Item DRIED_ROOTS_AXE = regItem("dried_roots_axe",new Dried_roots_axe(DirtyToolMaterials.DRIED_ROOTS,9,-3,new Item.Settings()));
    public static final Item DRIED_ROOTS_HELMET = regItem("dried_roots_helmet",new DriedRootsArmorItem(DirtyArmorMaterials.DRIED_ROOTS, ArmorItem.Type.HELMET,new Item.Settings()));
    public static final Item DRIED_ROOTS_CHESTPLATE = regItem("dried_roots_chestplate",new DriedRootsArmorItem(DirtyArmorMaterials.DRIED_ROOTS,ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item DRIED_ROOTS_LEGGINGS = regItem("dried_roots_leggings",new DriedRootsArmorItem(DirtyArmorMaterials.DRIED_ROOTS, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item DRIED_ROOTS_BOOTS = regItem("dried_roots_boots",new DriedRootsArmorItem(DirtyArmorMaterials.DRIED_ROOTS, ArmorItem.Type.BOOTS,new Item.Settings()));
    public static final Item WHITE_ROOTS_HELMET = regItem("white_roots_helmet",new ArmorItem(DirtyArmorMaterials.WHITE_ROOTS, ArmorItem.Type.HELMET,new Item.Settings()));
    public static final Item WHITE_ROOTS_CHESTPLATE = regItem("white_roots_chestplate",new ArmorItem(DirtyArmorMaterials.WHITE_ROOTS, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item WHITE_ROOTS_LEGGINGS = regItem("white_roots_leggings",new ArmorItem(DirtyArmorMaterials.WHITE_ROOTS, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item WHITE_ROOTS_BOOTS = regItem("white_roots_boots",new ArmorItem(DirtyArmorMaterials.WHITE_ROOTS, ArmorItem.Type.BOOTS,new Item.Settings()));
    public static final Item ROOTS_SOUP = regItem("roots_soup", new StewItem(new Item.Settings().maxCount(16).food(FoodComponents.RABBIT_STEW)));
    public static final Item WHITE_ROOTS_PICKAXE = regItem("white_roots_pickaxe",new WhiteRootsPickaxeItem(DirtyToolMaterials.WHITE_ROOTS,8,-2.8f,new Item.Settings()));
    public static final Item WHITE_ROOTS_AXE = regItem("white_roots_axe",new AxeItem(DirtyToolMaterials.WHITE_ROOTS,12,-2.8f,new Item.Settings()));
    public static final Item WHITE_ROOTS_SWORD = regItem("white_roots_sword",new WhiteRootsSwordItem(DirtyToolMaterials.WHITE_ROOTS,11,-2.4f,new Item.Settings()));
    public static final Item WHITE_DUST = regItem("white_dust",new Item(new Item.Settings()));
    public static final Item WHITE_CRISTAL = regItem("white_cristal",new Item(new Item.Settings()));
    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(DRIED_ROOTS_SWORD))
            .displayName(Text.literal("Dirty"))
            .entries((context, entries) -> {
                entries.add(PIECE_OF_DIRT);
                entries.add(EXPLOSIVE_DIRT);
                entries.add(DRIED_ROOTS);
                entries.add(LIVING_ROOTS);
                entries.add(MUD_SPAWN_EGG);
                entries.add(DRIED_ROOTS_SWORD);
                entries.add(DRIED_ROOTS_STAFF);
                entries.add(DRIED_ROOTS_PICKAXE);
                entries.add(DRIED_ROOTS_AXE);
                entries.add(DRIED_ROOTS_HELMET);
                entries.add(DRIED_ROOTS_CHESTPLATE);
                entries.add(DRIED_ROOTS_LEGGINGS);
                entries.add(DRIED_ROOTS_BOOTS);
                entries.add(ROOTS_SOUP);
                entries.add(MELTED_DIRT);
                entries.add(PILE_OF_DIRT);
                entries.add(BIOFUEL);
                entries.add(BURNT_IRON_SHEET);
                entries.add(NETHERITE_HAMMER);
                entries.add(DIAMOND_HAMMER);
                entries.add(IRON_HAMMER);
                entries.add(GOLDEN_HAMMER);
                entries.add(STONE_HAMMER);
                entries.add(WOODEN_HAMMER);
                entries.add(IRON_SHEET);
                entries.add(LOOT_BOX);
                entries.add(RANDOM_POTION);
                entries.add(WHITE_ROOTS);
                entries.add(WHITE_ROOTS_BOOTS);
                entries.add(WHITE_ROOTS_LEGGINGS);
                entries.add(WHITE_ROOTS_CHESTPLATE);
                entries.add(WHITE_ROOTS_HELMET);
                entries.add(WHITE_ROOTS_PICKAXE);
                entries.add(WHITE_ROOTS_AXE);
                entries.add(WHITE_ROOTS_SWORD);
                entries.add(WHITE_DUST);
                entries.add(DirtyBlocks.DIRT_BLOCK_ITEM);
                entries.add(DirtyBlocks.DIRT_RECYCLER_ITEM);
                entries.add(DirtyBlocks.PROCESSING_TABLE_ITEM);
                entries.add(DirtyBlocks.SIEVE_ITEM);
                entries.add(DirtyBlocks.WHITE_DUST_ORE_ITEM);
            })
            .build();
    private static Item regItem(String id, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Dirty.MOD_ID, id), item);
    }
    private static void addToCombat(FabricItemGroupEntries entries){
        entries.add(PIECE_OF_DIRT);
        entries.add(EXPLOSIVE_DIRT);
        entries.add(DRIED_ROOTS_SWORD);
        entries.add(DRIED_ROOTS_HELMET);
        entries.add(DRIED_ROOTS_CHESTPLATE);
        entries.add(DRIED_ROOTS_LEGGINGS);
        entries.add(DRIED_ROOTS_BOOTS);
        entries.add(DRIED_ROOTS_STAFF);
    }

    private static void addToTools(FabricItemGroupEntries entries){
        entries.add(DRIED_ROOTS_PICKAXE);
        entries.add(DRIED_ROOTS_AXE);
        entries.add(NETHERITE_HAMMER);
        entries.add(DIAMOND_HAMMER);
        entries.add(IRON_HAMMER);
        entries.add(GOLDEN_HAMMER);
        entries.add(STONE_HAMMER);
        entries.add(WOODEN_HAMMER);
    }
    private static void addToEggs(FabricItemGroupEntries entries){
        entries.add(MUD_SPAWN_EGG);
    }
    private static void addToIngredients(FabricItemGroupEntries entries){
        entries.add(DRIED_ROOTS);
        entries.add(LIVING_ROOTS);
        entries.add(MELTED_DIRT);
        entries.add(PILE_OF_DIRT);
        entries.add(WHITE_DUST);
    }

    public static void register(){
        Registry.register(Registries.ITEM_GROUP, new Identifier(Dirty.MOD_ID, "maingroup"), ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(DirtyItems::addToCombat);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(DirtyItems::addToTools);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(DirtyItems::addToEggs);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(DirtyItems::addToIngredients);
    }
}
