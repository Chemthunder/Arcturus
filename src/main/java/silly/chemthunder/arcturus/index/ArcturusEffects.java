package silly.chemthunder.arcturus.index;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import silly.chemthunder.arcturus.Arcturus;
import silly.chemthunder.arcturus.effect.DespairEffect;
import silly.chemthunder.arcturus.effect.SillyChainThatLocksPeopleInPlaceStatusEffect;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public interface ArcturusEffects {
    Map<StatusEffect, Identifier> EFFECTS = new LinkedHashMap<>();

    StatusEffect CHAINED = create("chained", new SillyChainThatLocksPeopleInPlaceStatusEffect(StatusEffectCategory.NEUTRAL, 0x00000));
    StatusEffect DESPAIR = create("despair", new DespairEffect(StatusEffectCategory.NEUTRAL, 0x00000).addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH, UUID.randomUUID().toString(), -2, EntityAttributeModifier.Operation.ADDITION));

    static void initialize() {
        EFFECTS.keySet().forEach(effect -> Registry.register(Registries.STATUS_EFFECT, EFFECTS.get(effect), effect));
    }

    private static <T extends StatusEffect> T create(String name, T effect) {
        EFFECTS.put(effect, Arcturus.id(name));
        return effect;
    }
}