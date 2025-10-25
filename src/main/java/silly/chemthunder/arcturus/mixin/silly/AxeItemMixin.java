package silly.chemthunder.arcturus.mixin.silly;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin extends MiningToolItem {
    public AxeItemMixin(float attackDamage, float attackSpeed, ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ItemStack offStack = attacker.getOffHandStack();

        if (offStack.isOf(Items.END_CRYSTAL)) {
            offStack.decrement(1);
            target.setVelocity(attacker.getRotationVec(0).multiply(3));
            attacker.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1, 1);
        }
        return super.postHit(stack, target, attacker);
    }
}
