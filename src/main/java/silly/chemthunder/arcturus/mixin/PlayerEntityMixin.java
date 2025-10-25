package silly.chemthunder.arcturus.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import silly.chemthunder.arcturus.entity.CreatureEntity;
import silly.chemthunder.arcturus.index.ArcturusEffects;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow
    public abstract boolean giveItemStack(ItemStack stack);

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean isSneaking() {
        if (this.getVehicle() instanceof CreatureEntity) {
            return false;
        }
        return super.isSneaking();
    }

    @ModifyReturnValue(method = "getMovementSpeed", at = @At("RETURN"))
    public float getMovementSpeed(float original) {
        if (this.hasStatusEffect(ArcturusEffects.DESPAIR)) {
                return original / 1.4f;
        }
        return original;
    }
}
