package com.unk.superfishingrod;

import com.unk.superfishingrod.datagen.ModelProvider;
import com.unk.superfishingrod.datagen.translation.EnUsProvider;
import com.unk.superfishingrod.datagen.translation.ZhCnProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SuperFishingRodDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModelProvider::new);
		pack.addProvider(ZhCnProvider::new);
		pack.addProvider(EnUsProvider::new);
	}
}
