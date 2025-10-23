package silly.chemthunder.arcturus.index;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import silly.chemthunder.arcturus.Arcturus;
import silly.chemthunder.arcturus.entity.CreatureEntity;
import silly.chemthunder.arcturus.entity.MissileEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public interface ArcturusEntities {
    Map<EntityType<? extends Entity>, Identifier> ENTITIES = new LinkedHashMap<>();

    //  Item WEAPON_RACK = create("weapon_rack", new WeaponRackItem(new Item.Settings()));

    EntityType<CreatureEntity> CREATURE = createEntity("creature", FabricEntityTypeBuilder.<CreatureEntity>create(SpawnGroup.MISC, CreatureEntity::new).disableSaving().dimensions(EntityDimensions.changing(1f, 1f)).build());
    EntityType<MissileEntity> MISSILE = createEntity("missile", FabricEntityTypeBuilder.<MissileEntity>create(SpawnGroup.MISC, MissileEntity::new).disableSaving().dimensions(EntityDimensions.changing(0.2f, 0.2f)).build());


    private static <T extends EntityType<? extends Entity>> T createEntity(String name, T entity) {
        ENTITIES.put(entity, new Identifier(Arcturus.MOD_ID, name));
        return entity;
    }

    static void initialize() {
        ENTITIES.keySet().forEach(entityType -> Registry.register(Registries.ENTITY_TYPE, ENTITIES.get(entityType), entityType));
    }
}
