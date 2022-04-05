package baguchan.endenviron.world.gen;

import baguchan.endenviron.EndEnviron;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountOnEveryLayerPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacements {
	public static final Holder<PlacedFeature> PATCH_END_GRASS = PlacementUtils.register(prefix("patch_end_grass"), ModConfiguredFeatures.PATCH_END_GRASS, CountOnEveryLayerPlacement.of(6), BiomeFilter.biome());


	public static String prefix(String name) {
		return EndEnviron.MODID + ":" + name;
	}


	public static void init() {

	}
}
