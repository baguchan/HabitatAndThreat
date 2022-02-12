package baguchan.endenviron.world.gen;

import baguchan.endenviron.EndEnviron;
import baguchan.endenviron.registry.ModBlocks;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModConfiguredFeatures {
	public static final ConfiguredFeature<?, ?> PATCH_END_GRASS = FeatureUtils.register(prefix("patch_end_grass"), Feature.RANDOM_PATCH.configured(grassPatch(BlockStateProvider.simple(ModBlocks.END_GRASS.get()), 32)));

	private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
		return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(p_195203_)).onlyWhenEmpty());
	}

	public static String prefix(String name) {
		return EndEnviron.MODID + ":" + name;
	}
}
