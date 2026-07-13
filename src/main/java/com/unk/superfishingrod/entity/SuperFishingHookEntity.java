package com.unk.superfishingrod.entity;

import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

import static com.unk.superfishingrod.SuperFishingRod.RANDOM_ENTITIES;
import static com.unk.superfishingrod.SuperFishingRod.RANDOM_ITEMS;

public class SuperFishingHookEntity extends FishingHook {
    public SuperFishingHookEntity(Player player, Level level, int luck, int lureSpeed) {
        super(player, level, luck, lureSpeed);
    }

    public SuperFishingHookEntity(EntityType<@NotNull SuperFishingHookEntity> superFishingHookEntityEntityType, Level level) {
        super(superFishingHookEntityEntityType, level);
    }

    @Override
    public int retrieve(@NotNull ItemStack rod) {
        Player owner = this.getPlayerOwner();
        if (!this.level().isClientSide() && owner != null && !this.shouldStopFishing(owner)) {
            int dmg = 0;
            if (this.hookedIn != null) {
                this.pullEntity(this.hookedIn);
                CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayer)owner, rod, this, Collections.emptyList());
                this.level().broadcastEntityEvent(this, (byte)31);
                dmg = this.hookedIn instanceof ItemEntity ? 3 : 5;
            } else if (this.nibble > 0) {
                Level level = this.level();
                RandomSource random = this.getRandom();

                if (random.nextDouble() <= 0.85) {
                    Item item = RANDOM_ITEMS.get(level.getRandom().nextInt(RANDOM_ITEMS.size()));

                    ItemStack stack = new ItemStack(item);

                    ItemEntity entity = new ItemEntity(level, this.getX(), this.getY(), this.getZ(), stack);
                    double dx = owner.getX() - this.getX();
                    double dy = owner.getY() - this.getY();
                    double dz = owner.getZ() - this.getZ();
                    entity.setDeltaMovement(dx * 0.1, dy * 0.1 + Math.sqrt(Math.sqrt(dx*dx + dy*dy + dz*dz)) * 0.08, dz * 0.1);
                    level.addFreshEntity(entity);
                } else {
                    EntityType<?> type = RANDOM_ENTITIES.get(level.getRandom().nextInt(RANDOM_ENTITIES.size()));

                    Mob mob = (Mob) type.create(level, EntitySpawnReason.NATURAL);

                    if (mob != null) {
                        mob.setPos(this.getX(), this.getY(), this.getZ());
                        mob.finalizeSpawn((ServerLevel) level, ((ServerLevel) level).getCurrentDifficultyAt(mob.blockPosition()),
                                EntitySpawnReason.NATURAL, null);
                        level.addFreshEntity(mob);


                        this.pullEntity(mob);
                        CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayer) owner, rod, this, Collections.emptyList());
                        level.broadcastEntityEvent(this, (byte) 31);
                    }
                }

                dmg = 1;

            }

            if (this.onGround()) {
                dmg = 2;
            }

            this.discard();
            return dmg;
        } else {
            return 0;
        }
    }

    @Override
    public void pullEntity(@NotNull Entity entity) {
        Entity owner = this.getOwner();
        if (owner != null) {
            Vec3 delta = (new Vec3((owner.getX() - this.getX()), owner.getY() - this.getY(), owner.getZ() - this.getZ()))
                    .scale(0.2 );
            entity.setDeltaMovement(entity.getDeltaMovement().add(delta));
        }
    }
}
