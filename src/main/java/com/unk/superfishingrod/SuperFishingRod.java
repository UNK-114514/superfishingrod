package com.unk.superfishingrod;

import com.unk.superfishingrod.entity.ModEntityTypes;
import com.unk.superfishingrod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SuperFishingRod implements ModInitializer {
	public static final String MOD_ID = "superfishingrod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModEntityTypes.initialize();
		EntityRenderers.register(ModEntityTypes.SUPER_FISHING_HOOK, FishingHookRenderer::new);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
