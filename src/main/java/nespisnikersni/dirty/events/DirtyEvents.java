package nespisnikersni.dirty.events;

import nespisnikersni.dirty.enchants.DirtyEnchants;
import nespisnikersni.dirty.items.DirtyItems;
import nespisnikersni.dirty.items.WhiteRootsAxeItem;
import nespisnikersni.dirty.items.WhiteRootsPickaxeItem;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class DirtyEvents implements PlayerBlockBreakEvents.After, ServerLivingEntityEvents.AllowDamage, AttackEntityCallback {
    @Override
    public void afterBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        if (EnchantmentHelper.getLevel(DirtyEnchants.BOER, player.getMainHandStack()) > 0 && !player.isSneaking() || player.getMainHandStack().getItem() instanceof WhiteRootsPickaxeItem pickaxe && pickaxe.mode == 1) {
            int radius = 1;
            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        BlockPos currentPos = pos.add(x, y, z);
                        BlockState blockState = world.getBlockState(currentPos);
                        if (blockState.getHardness(world, currentPos) >= 0 && player.canPlaceOn(currentPos, Direction.UP, player.getMainHandStack())) {
                            if (!player.isCreative()) {
                                player.getMainHandStack().damage(1, player, (p) -> {
                                    p.sendToolBreakStatus(player.getActiveHand());
                                });
                            }
                                world.breakBlock(currentPos, false, player);
                                if(EnchantmentHelper.getLevel(DirtyEnchants.AUTO_SMELT, player.getMainHandStack()) > 0) {
                                    LootContextParameterSet.Builder builder = new LootContextParameterSet.Builder((ServerWorld) world);
                                    builder.add(LootContextParameters.TOOL, player.getMainHandStack());
                                    builder.add(LootContextParameters.BLOCK_STATE, blockState);
                                    builder.add(LootContextParameters.ORIGIN, new Vec3d(pos.getX(), pos.getY(), pos.getZ()));
                                    List<ItemStack> drops = blockState.getDroppedStacks(builder);
                                    drops = getAutoSmeltedDrops(world,drops);
                                    for (ItemStack drop : drops) {
                                        Block.dropStack(world, currentPos, drop);
                                    }
                                }else{
                                    LootContextParameterSet.Builder builder = new LootContextParameterSet.Builder((ServerWorld) world);
                                    builder.add(LootContextParameters.TOOL, player.getMainHandStack());
                                    builder.add(LootContextParameters.BLOCK_STATE, blockState);
                                    builder.add(LootContextParameters.ORIGIN, new Vec3d(pos.getX(), pos.getY(), pos.getZ()));
                                    List<ItemStack> drops = blockState.getDroppedStacks(builder);
                                    for (ItemStack drop : drops) {
                                        Block.dropStack(world, currentPos, drop);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        if ((player.getMainHandStack().getItem() instanceof WhiteRootsPickaxeItem pickaxe && pickaxe.mode == 2)) {
            int radius = 2;

            if (player.getPitch() > 45) {
                for (int x = -radius; x <= radius; x++) {
                        for (int z = -radius; z <= radius; z++) {
                            BlockPos currentPos = pos.add(x, 0, z);
                            breakBlock(world, player, currentPos);
                        }
                }
            } else if (player.getPitch() < -45) {
                for (int x = -radius; x <= radius; x++) {
                        for (int z = -radius; z <= radius; z++) {
                            BlockPos currentPos = pos.add(x, 0, z);
                            breakBlock(world, player, currentPos);
                        }
                }
            } else {
                Direction direction = player.getHorizontalFacing();
                if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                    for (int x = -radius; x <= radius; x++) {
                        for (int y = -radius; y <= radius; y++) {
                            BlockPos currentPos = pos.add(x, y, 0);
                            breakBlock(world, player, currentPos);
                        }
                    }
                } else if (direction == Direction.EAST || direction == Direction.WEST) {
                    for (int z = -radius; z <= radius; z++) {
                        for (int y = -radius; y <= radius; y++) {
                            BlockPos currentPos = pos.add(0, y, z);
                            breakBlock(world, player, currentPos);
                        }
                    }
                }
            }
        }
        if(player.getMainHandStack().getItem() instanceof WhiteRootsAxeItem axe){
            if(!world.isClient){
                chopTree(world, pos);
            }
        }
    }
    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        LivingEntity attacker = (LivingEntity) source.getAttacker();
        if(attacker instanceof Entity && entity instanceof PlayerEntity player && haveWRArmor(player)) {
            knockbackEntity(attacker, player);
        }
        return true;
    }
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        int level = EnchantmentHelper.getLevel(DirtyEnchants.ATTRACTION,player.getStackInHand(hand));
        if(level > 0 && player.getAttackCooldownProgress(0.5f) >= 1.0f){
            Vec3d pullDirection = player.getPos().subtract(entity.getPos()).normalize().multiply(switch(level){
                case 1 -> 0.9;
                case 2 -> 1.1;
                case 3 -> 1.3;
                default -> 0;
                    });
            entity.addVelocity(pullDirection.x, pullDirection.y, pullDirection.z);
        }
        return ActionResult.PASS;
    }
    private void breakBlock(World world, PlayerEntity player, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.getHardness(world, pos) >= 0 && player.canPlaceOn(pos, Direction.UP, player.getMainHandStack())) {
            world.breakBlock(pos,false,player);
            if (!player.isCreative()) {
                player.getMainHandStack().damage(1, player, (p) -> {
                    p.sendToolBreakStatus(player.getActiveHand());
                });
            }
            if(EnchantmentHelper.getLevel(DirtyEnchants.AUTO_SMELT, player.getMainHandStack()) > 0) {
                LootContextParameterSet.Builder builder = new LootContextParameterSet.Builder((ServerWorld) world);
                builder.add(LootContextParameters.TOOL, player.getMainHandStack());
                builder.add(LootContextParameters.BLOCK_STATE, blockState);
                builder.add(LootContextParameters.ORIGIN, new Vec3d(pos.getX(), pos.getY(), pos.getZ()));
                List<ItemStack> drops = blockState.getDroppedStacks(builder);
                drops = getAutoSmeltedDrops(world,drops);
                for (ItemStack drop : drops) {
                    Block.dropStack(world, pos, drop);
                }
            }else{
                LootContextParameterSet.Builder builder = new LootContextParameterSet.Builder((ServerWorld) world);
                builder.add(LootContextParameters.TOOL, player.getMainHandStack());
                builder.add(LootContextParameters.BLOCK_STATE, blockState);
                builder.add(LootContextParameters.ORIGIN, new Vec3d(pos.getX(), pos.getY(), pos.getZ()));
                List<ItemStack> drops = blockState.getDroppedStacks(builder);
                for (ItemStack drop : drops) {
                    Block.dropStack(world, pos, drop);
                }
            }
        }
    }


    private static void knockbackEntity(LivingEntity entity, LivingEntity target) {
        double strength = -1.5;
        double dx = target.getX() - entity.getX();
        double dz = target.getZ() - entity.getZ();
        double distance = Math.sqrt(dx * dx + dz * dz);

        if (distance != 0.0D) {
            entity.addVelocity(dx / distance * strength, 0.1D, dz / distance * strength);
        }
    }
    private static boolean haveWRArmor(PlayerEntity player){
        return player.getInventory().getArmorStack(0).getItem()==DirtyItems.WHITE_ROOTS_BOOTS&&player.getInventory().getArmorStack(1).getItem()==DirtyItems.WHITE_ROOTS_LEGGINGS
                &&player.getInventory().getArmorStack(2).getItem()==DirtyItems.WHITE_ROOTS_CHESTPLATE&&player.getInventory().getArmorStack(3).getItem()==DirtyItems.WHITE_ROOTS_HELMET;
    }
    public static List<ItemStack> getAutoSmeltedDrops(World world, List<ItemStack> originalDrops) {
        List<ItemStack> smeltedDrops = new ArrayList<>();
        for (ItemStack itemStack : originalDrops) {
            Optional<SmeltingRecipe> recipe = world.getRecipeManager().getFirstMatch(RecipeType.SMELTING, new SimpleInventory(itemStack), world);
            DynamicRegistryManager registryManager = world.getRegistryManager();
            if (recipe.isPresent()) {
                ItemStack smelted = recipe.get().getOutput(registryManager).copy();
                smelted.setCount(itemStack.getCount());
                smeltedDrops.add(smelted);
            } else {
                smeltedDrops.add(itemStack);
            }
        }
        return smeltedDrops;
    }
    private void chopTree(World world, BlockPos pos) {
        Queue<BlockPos> blocksToCheck = new LinkedList<>();
        blocksToCheck.add(pos);

        while (!blocksToCheck.isEmpty()) {
            BlockPos currentPos = blocksToCheck.poll();
            BlockState currentState = world.getBlockState(currentPos);

            if (currentState.isIn(BlockTags.LOGS)) {
                world.breakBlock(currentPos, true);
                for (Direction direction : Direction.values()) {
                    blocksToCheck.add(currentPos.offset(direction));
                }
            }
        }
    }
    public static void register(){
        PlayerBlockBreakEvents.AFTER.register(new DirtyEvents());
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(new DirtyEvents());
        AttackEntityCallback.EVENT.register(new DirtyEvents());
    }

}


