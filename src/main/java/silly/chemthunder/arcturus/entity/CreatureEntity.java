package silly.chemthunder.arcturus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
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
        return false;
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
}
