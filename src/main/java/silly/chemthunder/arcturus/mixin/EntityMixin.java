package silly.chemthunder.arcturus.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.util.Nameable;
import net.minecraft.world.entity.EntityLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import silly.chemthunder.arcturus.index.ArcturusEffects;

@Mixin(Entity.class)
public abstract class EntityMixin implements Nameable, EntityLike, CommandOutput, AttachmentTarget {
    @ModifyReturnValue(method = "isTeammate", at = @At("RETURN"))
    public boolean arsenal$preventStunnedMobsFromTargeting(boolean original) {
        if ((Object) this instanceof LivingEntity livingEntity) {
            return livingEntity.hasStatusEffect(ArcturusEffects.CHAINED);
        }
        return original;
    }
}
