package baguchan.habitat_and_threat.world.gen;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.registry.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModConfiguredFeatures {
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_END_GRASS = FeatureUtils.register(prefix("patch_end_grass"), Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(ModBlocks.END_GRASS.get()), 32));
	public static final Holder<ConfiguredFeature<LakeFeature.Configuration, ?>> SLIMEND_LAKE = FeatureUtils.register(prefix("slimend_spring"), Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(ModBlocks.SLIMEND.get().defaultBlockState()), BlockStateProvider.simple(ModBlocks.SLIMEND_MUD.get().defaultBlockState())));


	private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
		return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(p_195203_)));
	}

	public static String prefix(String name) {
		return HabitatAndThreat.MODID + ":" + name;
	}
}
