package silly.chemthunder.arcturus.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.acoyt.acornlib.impl.init.AcornParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import silly.chemthunder.arcturus.index.ArcturusDamageSources;
import silly.chemthunder.arcturus.index.ArcturusEffects;

import java.util.UUID;

public class DecadenceItem extends SwordItem implements CustomHitSoundItem, CustomHitParticleItem, CustomKillSourceItem {
    public DecadenceItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

//    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0xf5bae8, 0xd675c0)};

    @Override
    public void playHitSound(PlayerEntity playerEntity, Entity entity) {
        playerEntity.playSound(SoundEvents.ITEM_CROSSBOW_LOADING_END, 1, 1);
    }

    public void spawnHitParticles(PlayerEntity player, Entity entity) {
        double deltaX = -MathHelper.sin((float) (player.getYaw() * (Math.PI / 180.0F)));
        double deltaZ = MathHelper.cos((float) (player.getYaw() * (Math.PI / 180.0F)));
        World var7 = player.getWorld();
        if (var7 instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    AcornParticles.ALT_GOLD_SWEEP,
                    player.getX() + deltaX,
                    player.getBodyY(0.5F),
                    player.getZ() + deltaZ,
                    0, deltaX, 0.0F, deltaZ, 0.0F
            );
        }
    }

    @Override
    public DamageSource getKillSource(LivingEntity livingEntity) {
        return ArcturusDamageSources.decadence_kill(livingEntity);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.hasStatusEffect(ArcturusEffects.DESPAIR)) {
            target.addStatusEffect(new StatusEffectInstance(ArcturusEffects.DESPAIR, 100));
        }
        if (target.hasStatusEffect(ArcturusEffects.DESPAIR)) {

        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return ImmutableMultimap.<EntityAttribute, EntityAttributeModifier>builder()
                    .putAll(super.getAttributeModifiers(stack, slot))
                    .put(ReachEntityAttributes.REACH, new EntityAttributeModifier(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), "Reach", 1, EntityAttributeModifier.Operation.ADDITION))
                    .put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(UUID.fromString("123e4567-e89b-12d3-a456-426614174001"), "Entity Interaction Range", 1, EntityAttributeModifier.Operation.ADDITION))
                    .build();
        } else {
            return super.getAttributeModifiers(slot);
        }
    }
}
