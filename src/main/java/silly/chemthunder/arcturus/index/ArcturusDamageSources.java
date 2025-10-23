package silly.chemthunder.arcturus.index;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import silly.chemthunder.arcturus.Arcturus;

public interface ArcturusDamageSources {
    RegistryKey<DamageType> DECADENCE_KILL = of("decadence_kill");

    static DamageSource decadence_kill(LivingEntity entity) {
        return entity.getDamageSources().create(DECADENCE_KILL); }

    RegistryKey<DamageType> GRIMOIRE = of("grimoire");

    static DamageSource grimoire(Entity entity) {
        return entity.getDamageSources().create(GRIMOIRE); }

    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Arcturus.id(name));
    }
}
