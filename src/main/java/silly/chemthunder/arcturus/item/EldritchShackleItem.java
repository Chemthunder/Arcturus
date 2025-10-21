package silly.chemthunder.arcturus.item;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import silly.chemthunder.arcturus.index.ArcturusEffects;

import java.util.Objects;

public class EldritchShackleItem extends Item {
    public EldritchShackleItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {

        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        user.sendMessage(Text.literal(entity.getType() + " was chained by a higher deity"));
        stack.decrement(1);
        World world = user.getWorld();

        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(EntityType.AREA_EFFECT_CLOUD, user.getWorld());
        areaEffectCloudEntity.setParticleType(ParticleTypes.END_ROD);
        areaEffectCloudEntity.setPos(entity.getX(), entity.getY(), entity.getZ());

        world.spawnEntity(areaEffectCloudEntity);

        entity.addStatusEffect(new StatusEffectInstance(ArcturusEffects.CHAINED, Integer.MAX_VALUE));
        user.playSound(SoundEvents.BLOCK_CONDUIT_AMBIENT_SHORT, 1, 1);
        user.playSound(SoundEvents.BLOCK_BEACON_POWER_SELECT, 1, 1);
        entity.startRiding(areaEffectCloudEntity);
        return super.useOnEntity(stack, user, entity, hand);
    }
}
