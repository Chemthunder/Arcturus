package silly.chemthunder.arcturus.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import silly.chemthunder.arcturus.index.ArcturusEffects;

public class DamnedBookItem extends Item {
    public DamnedBookItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return super.getMaxUseTime(stack);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(ArcturusEffects.CHAINED, 50));
        attacker.addStatusEffect(new StatusEffectInstance(ArcturusEffects.CHAINED, 50));


        return super.postHit(stack, target, attacker);
    }
}
