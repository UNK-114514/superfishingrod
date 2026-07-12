package com.unk.superfishingrod.datagen.translation;

import com.unk.superfishingrod.entity.ModEntityTypes;
import com.unk.superfishingrod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class EnUsProvider extends FabricLanguageProvider {
    public EnUsProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.@NotNull Provider provider, FabricLanguageProvider.TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.SUPER_FISHING_ROD, "Super Fishing Rod");
        translationBuilder.add(ModEntityTypes.SUPER_FISHING_HOOK, "Super Hook");
    }

    @Override
    public @NotNull String getName() {
        return "SuperFishingRodEnUs";
    }
}
