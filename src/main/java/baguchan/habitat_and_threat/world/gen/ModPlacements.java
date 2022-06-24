package baguchan.habitat_and_threat.world.gen;

import baguchan.habitat_and_threat.HabitatAndThreat;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.*;

public class ModPlacements {
	public static final Holder<PlacedFeature> PATCH_END_GRASS = PlacementUtils.register(prefix("patch_end_grass"), ModConfiguredFeatures.PATCH_END_GRASS, CountOnEveryLayerPlacement.of(6), BiomeFilter.biome());
	public static final Holder<PlacedFeature> LAKE_SLIMEND_SURFACE = PlacementUtils.register(prefix("slimend_lake"), ModConfiguredFeatures.SLIMEND_LAKE, RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());


	public static String prefix(String name) {
		return HabitatAndThreat.MODID + ":" + name;
	}


	public static void init() {

	}
}
