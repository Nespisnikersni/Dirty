package nespisnikersni.dirty;

import nespisnikersni.dirty.datagen.*;
import nespisnikersni.dirty.world.biomes.DirtyBiomes;
import nespisnikersni.dirty.world.DirtyConfiguredFeatures;
import nespisnikersni.dirty.world.DirtyPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class DirtyDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ItemModelProvider::new);
        pack.addProvider(DirtyWorldGen::new);
        pack.addProvider(DirtyBlockTagProvider::new);
        pack.addProvider(DirtyBlockLootTableProvider::new);
        pack.addProvider(DirtyRecipeProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.BIOME, DirtyBiomes::boostrap);
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, DirtyConfiguredFeatures::boostrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, DirtyPlacedFeatures::boostrap);
    }
}