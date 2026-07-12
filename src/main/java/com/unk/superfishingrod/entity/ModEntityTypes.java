package com.unk.superfishingrod.entity;

import com.unk.superfishingrod.SuperFishingRod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.jetbrains.annotations.NotNull;

public class ModEntityTypes {
    public static final EntityType<@NotNull SuperFishingHookEntity> SUPER_FISHING_HOOK = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.tryBuild(SuperFishingRod.MOD_ID, "super_fishing_hook")),
            EntityType.Builder.<SuperFishingHookEntity>of(SuperFishingHookEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(5)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.tryBuild(SuperFishingRod.MOD_ID, "super_fishing_hook")))
    );

    public static void initialize() {

    }
}
