package silly.chemthunder.arcturus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CreatureEntity extends Entity {
    public CreatureEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public boolean canHit() {
        return true;
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater positionUpdater) {
        if (!this.hasPassenger(passenger)) {
            return;
        }
        passenger.setPosition(this.getX(), this.getY() - 0.3, this.getZ());
        passenger.setBodyYaw(MathHelper.clamp(passenger.getYaw(), this.getYaw() - 10f, this.getYaw() + 10f));
        super.updatePassengerPosition(passenger, positionUpdater);
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (player.isSneaking()) {
            this.kill();

            Entity attacker = player.getControllingVehicle();
            if (attacker instanceof LivingEntity livingEntity) {
                livingEntity.clearStatusEffects();
            }
        }
        return super.interact(player, hand);
    }

    @Override
    public void onDamaged(DamageSource damageSource) {
        this.kill();
        super.onDamaged(damageSource);
    }
}
