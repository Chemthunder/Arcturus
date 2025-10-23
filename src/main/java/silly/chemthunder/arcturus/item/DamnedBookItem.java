package silly.chemthunder.arcturus.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import silly.chemthunder.arcturus.entity.MissileEntity;
import silly.chemthunder.arcturus.index.ArcturusEntities;

public class DamnedBookItem extends Item {
    public DamnedBookItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return super.getMaxUseTime(stack);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world instanceof ServerWorld serverWorld) {
            MissileEntity missile = new MissileEntity(ArcturusEntities.MISSILE, world);
            Vec3d pos = user.getPos();
            missile.updatePosition(pos.x, pos.y + 1.5f, pos.z);
            missile.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 7.5F * 0.5F, 0.0F);
            serverWorld.spawnEntity(missile);
            user.playSound(SoundEvents.BLOCK_BEACON_AMBIENT, 1, 1);
        }
        return super.use(world, user, hand);
    }
}
