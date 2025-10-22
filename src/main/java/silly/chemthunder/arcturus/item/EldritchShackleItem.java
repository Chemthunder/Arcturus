package silly.chemthunder.arcturus.item;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import silly.chemthunder.arcturus.entity.CreatureEntity;
import silly.chemthunder.arcturus.index.ArcturusEffects;
import silly.chemthunder.arcturus.index.ArcturusEntities;

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
        World world = user.getWorld();
        stack.decrement(1);
        if (world instanceof ServerWorld serverWorld) {
            for (ServerPlayerEntity player : serverWorld.getPlayers()) {
                player.sendMessage(Text.translatable(entity.getType().getTranslationKey()).append(Text.literal(" was chained by a higher deity")), false);
            }
        }
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(EntityType.AREA_EFFECT_CLOUD, user.getWorld());
        areaEffectCloudEntity.setParticleType(ParticleTypes.END_ROD);
        areaEffectCloudEntity.setPos(entity.getX(), entity.getY(), entity.getZ());
        areaEffectCloudEntity.setDuration(15);

        CreatureEntity creatureEntity = new CreatureEntity(ArcturusEntities.CREATURE, user.getWorld());
        creatureEntity.setPos(entity.getX(), entity.getY() + 0.6, entity.getZ());


        world.spawnEntity(areaEffectCloudEntity);
        world.spawnEntity(creatureEntity);
        entity.startRiding(creatureEntity);

        entity.addStatusEffect(new StatusEffectInstance(ArcturusEffects.CHAINED, Integer.MAX_VALUE));
        user.playSound(SoundEvents.BLOCK_CONDUIT_AMBIENT_SHORT, 1, 1);
        user.playSound(SoundEvents.BLOCK_BEACON_POWER_SELECT, 1, 1);
        return super.useOnEntity(stack, user, entity, hand);
    }
}
