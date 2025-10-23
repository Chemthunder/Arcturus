package silly.chemthunder.arcturus.item;

import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import silly.chemthunder.arcturus.index.ArcturusItems;

import java.util.List;

public class TatteredDamnedBookItem extends Item {
    public TatteredDamnedBookItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();
        Hand hand = player.getActiveHand();
        ItemStack stack = player.getStackInHand(hand);

        if (world.getBlockState(pos).isOf(Blocks.ENCHANTING_TABLE)) {
            stack.decrement(1);
            player.giveItemStack(ArcturusItems.DAMNED_BOOK.getDefaultStack());
        }
        return super.useOnBlock(context);
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
            tooltip.add(this.getUseEmpty());
    }

    public MutableText getUseEmpty() {
        return Text.translatable("text.book.canfix").formatted(Formatting.ITALIC).formatted(Formatting.GOLD).formatted(Formatting.OBFUSCATED);
    }
}
