package com.unk.superfishingrod;

import com.unk.superfishingrod.entity.ModEntityTypes;
import com.unk.superfishingrod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SuperFishingRod implements ModInitializer {
	public static final String MOD_ID = "superfishingrod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final List<Item> RANDOM_ITEMS = new ArrayList<>();
	public static final List<EntityType<?>> RANDOM_ENTITIES = new ArrayList<>();

	@Override
	public void onInitialize() {
		initList();

		ModItems.initialize();
		ModEntityTypes.initialize();
		EntityRenderers.register(ModEntityTypes.SUPER_FISHING_HOOK, FishingHookRenderer::new);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	public static void initList() {
		BuiltInRegistries.ITEM.forEach(item -> {
			if (item != Items.AIR) {
				RANDOM_ITEMS.add(item);
				System.out.println(item);
			}
		});

		BuiltInRegistries.ENTITY_TYPE.forEach(entityType -> {
			if (isMob(entityType) && (!isBoss(entityType))) {
				System.out.println(entityType.getBaseClass().descriptorString());
				RANDOM_ENTITIES.add(entityType);
			}
		});
	}

	public static boolean isBoss(EntityType<?> type) {
		return type == EntityTypes.ENDER_DRAGON || type == EntityTypes.WITHER;
	}

	public static boolean isMob(EntityType<?> type) {
		MobCategory cat = type.getCategory();
		return cat == MobCategory.CREATURE ||
				cat == MobCategory.MONSTER ||
				cat == MobCategory.AMBIENT ||
				cat == MobCategory.WATER_CREATURE ||
				cat == MobCategory.WATER_AMBIENT ||
				cat == MobCategory.AXOLOTLS;
	}
}
