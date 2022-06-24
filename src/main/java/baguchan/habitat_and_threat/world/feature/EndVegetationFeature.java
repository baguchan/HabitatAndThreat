package baguchan.habitat_and_threat.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraftforge.common.Tags;

public class EndVegetationFeature extends Feature<BlockStateConfiguration> {
	public EndVegetationFeature(Codec<BlockStateConfiguration> p_66361_) {
		super(p_66361_);
	}

	public boolean place(FeaturePlaceContext<BlockStateConfiguration> p_160068_) {
		WorldGenLevel worldgenlevel = p_160068_.level();
		BlockPos blockpos = p_160068_.origin();
		BlockState blockstate = worldgenlevel.getBlockState(blockpos.below());
		BlockStateConfiguration config = p_160068_.config();
		RandomSource random = p_160068_.random();
		if (!blockstate.is(Tags.Blocks.END_STONES)) {
			return false;
		} else {
			int i = blockpos.getY();
			if (i >= worldgenlevel.getMinBuildHeight() + 1 && i + 1 < worldgenlevel.getMaxBuildHeight()) {
				int j = 0;

				for (int k = 0; k < 4 * 4; ++k) {
					BlockPos blockpos1 = blockpos.offset(random.nextInt(4) - random.nextInt(4), random.nextInt(4) - random.nextInt(4), random.nextInt(4) - random.nextInt(4));
					BlockState blockstate1 = config.state;
					if (worldgenlevel.isEmptyBlock(blockpos1) && blockpos1.getY() > worldgenlevel.getMinBuildHeight() && blockstate1.canSurvive(worldgenlevel, blockpos1)) {
						worldgenlevel.setBlock(blockpos1, blockstate1, 2);
						++j;
					}
				}

				return j > 0;
			} else {
				return false;
			}
		}
	}
}