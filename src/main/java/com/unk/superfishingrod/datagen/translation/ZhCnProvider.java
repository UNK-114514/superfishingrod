package com.unk.superfishingrod.datagen.translation;

import com.unk.superfishingrod.entity.ModEntityTypes;
import com.unk.superfishingrod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ZhCnProvider extends FabricLanguageProvider {
    public ZhCnProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "zh_cn", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.@NotNull Provider provider, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.SUPER_FISHING_ROD, "超级钓竿");
        translationBuilder.add(ModEntityTypes.SUPER_FISHING_HOOK, "超级浮漂");
    }

    @Override
    public @NotNull String getName() {
        return "SuperFishingRodZhCn";
    }
}
