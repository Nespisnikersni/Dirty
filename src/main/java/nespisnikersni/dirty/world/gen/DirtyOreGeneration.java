package nespisnikersni.dirty.world.gen;

import nespisnikersni.dirty.world.DirtyPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class DirtyOreGeneration {
    public static void generateOres(){
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES, DirtyPlacedFeatures.WHITE_DUST_ORE_PLACED_KEY);
    }
}
