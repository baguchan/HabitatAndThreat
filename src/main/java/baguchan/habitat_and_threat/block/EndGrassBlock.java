package baguchan.habitat_and_threat.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

public class EndGrassBlock extends BushBlock {
	public EndGrassBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
		return p_51042_.is(Tags.Blocks.END_STONES);
	}
}