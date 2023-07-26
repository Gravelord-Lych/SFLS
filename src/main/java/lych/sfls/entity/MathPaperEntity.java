package lych.sfls.entity;

import lych.sfls.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class MathPaperEntity extends ProjectileItemEntity {
    public MathPaperEntity(EntityType<? extends ProjectileItemEntity> type, World world) {
        super(type, world);
    }

    public MathPaperEntity(double x, double y, double z, World world) {
        super(ModEntities.MATH_PAPER, x, y, z, world);
    }

    public MathPaperEntity(LivingEntity owner, World world) {
        super(ModEntities.MATH_PAPER, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.MATH_PAPER;
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            for (int i = 0; i < 8; ++i) {
                level.addParticle(new ItemParticleData(ParticleTypes.ITEM, getItem()), getX(), getY(), getZ(), ((double)random.nextFloat() - 0.5D) * 0.08D, ((double)random.nextFloat() - 0.5D) * 0.08D, ((double)random.nextFloat() - 0.5D) * 0.08D);
            }
        }
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult ray) {
        super.onHitEntity(ray);
        if (ray.getEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) ray.getEntity();
            if (entity.hurt(DamageSource.indirectMagic(this, getOwner()).bypassArmor().bypassMagic(), 8)) {
                entity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20 * 4, 6));
                entity.addEffect(new EffectInstance(Effects.WEAKNESS, 20 * 4, 3));
            }
        }
    }

    @Override
    protected void onHit(RayTraceResult ray) {
        super.onHit(ray);
        level.broadcastEntityEvent(this, (byte) 3);
        remove();
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected float getGravity() {
        return 0.02f;
    }
}
