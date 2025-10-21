package silly.chemthunder.arcturus.effect;

import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;

public class SillyChainThatLocksPeopleInPlaceStatusEffect extends StatusEffect {
    public SillyChainThatLocksPeopleInPlaceStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public ParticleEffect createParticle(StatusEffectInstance effect) {
        return new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.AIR.getDefaultState());
    }
}
