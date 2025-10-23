package silly.chemthunder.arcturus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import silly.chemthunder.arcturus.index.ArcturusDamageSources;
import silly.chemthunder.arcturus.index.ArcturusItems;

public class MissileEntity extends ThrownItemEntity {
    public MissileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public int lifeSpan = 0;

    @Override
    public void tick() {
        getWorld().addParticle(ParticleTypes.END_ROD, true, this.getX(), this.getY() + 0.1f, this.getZ(), 0, 0, 0);
        if (lifeSpan != 100) {
            lifeSpan++;
        } else if (lifeSpan == 100) {
            lifeSpan = 0;
            this.discard();
        }
        super.tick();
    }


    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, (byte)3);
            if (getWorld() instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(ParticleTypes.END_ROD, this.getX(), this.getY(), this.getZ(), 5, 0.02, 0.02, 0.02, 0.05);
            }
            this.discard();
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();

        entity.damage(ArcturusDamageSources.grimoire(entity), 1.5f);
        this.discard();
        super.onEntityHit(entityHitResult);
    }

    @Override
    protected Item getDefaultItem() {
        return ArcturusItems.DAMNED_BOOK;
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }
}
