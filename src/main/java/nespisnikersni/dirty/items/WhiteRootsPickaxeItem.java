package nespisnikersni.dirty.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class WhiteRootsPickaxeItem extends PickaxeItem {
    public int mode = 0;
    private Text modeName;

    public WhiteRootsPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
    public void switchMode(ItemStack stack) {
        mode = (mode + 1) % 3;
        stack.getOrCreateNbt().putInt("mode",mode);
        modeName= Text.translatable("dirty.pickaxe.mode"+String.valueOf(mode));
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        mode = stack.getOrCreateNbt().getInt("mode");
        if(!world.isClient()){
            if(user.isSneaking()){
                switchMode(stack);
                user.sendMessage(Text.translatable("dirty.pickaxe.mode").append(modeName).formatted(Formatting.YELLOW),true);
                return TypedActionResult.success(stack);
            }
        }
        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
