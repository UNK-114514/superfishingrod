package com.unk.superfishingrod.item;

import com.unk.superfishingrod.entity.SuperFishingHookEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public class SuperFishingRodItem extends FishingRodItem {
    public SuperFishingHookEntity fishingHook = null;

    public SuperFishingRodItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (player.fishing != null) {
            if (!level.isClientSide()) {
                player.fishing.retrieve(itemStack);
            }

            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            itemStack.causeUseVibration(player, GameEvent.ITEM_INTERACT_FINISH);
        } else {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            if (level instanceof ServerLevel serverLevel) {
                int luck = EnchantmentHelper.getFishingLuckBonus(serverLevel, itemStack, player);
                SuperFishingHookEntity hook = new SuperFishingHookEntity(player, level, luck, 580);
                Projectile.spawnProjectile(hook, serverLevel, itemStack);
                this.fishingHook = hook;
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            itemStack.causeUseVibration(player, GameEvent.ITEM_INTERACT_START);
        }

        return InteractionResult.SUCCESS;
    }
}
