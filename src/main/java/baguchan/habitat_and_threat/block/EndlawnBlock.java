package baguchan.habitat_and_threat.block;

import baguchan.habitat_and_threat.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LayerLightEngine;

import java.util.Random;

public class EndlawnBlock extends Block implements BonemealableBlock {
	public EndlawnBlock(Properties properties) {
		super(properties);
	}

	private static boolean canBeGrass(BlockState p_55079_, LevelReader p_55080_, BlockPos p_55081_) {
		BlockPos blockpos = p_55081_.above();
		BlockState blockstate = p_55080_.getBlockState(blockpos);
		int i = LayerLightEngine.getLightBlockInto(p_55080_, p_55079_, p_55081_, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(p_55080_, blockpos));
		return i < p_55080_.getMaxLightLevel();
	}

	public void randomTick(BlockState p_55074_, ServerLevel p_55075_, BlockPos p_55076_, Random p_55077_) {
	}

	public boolean isValidBonemealTarget(BlockGetter p_55064_, BlockPos p_55065_, BlockState p_55066_, boolean p_55067_) {
		return p_55064_.getBlockState(p_55065_.above()).isAir();
	}

	public boolean isBonemealSuccess(Level p_55069_, Random p_55070_, BlockPos p_55071_, BlockState p_55072_) {
		return true;
	}

	public void performBonemeal(ServerLevel p_55059_, Random p_55060_, BlockPos p_55061_, BlockState p_55062_) {
		for (BlockPos blockpos : BlockPos.betweenClosed(p_55061_.offset(-2, -2, -2), p_55061_.offset(2, 2, 2))) {
			BlockState blockstate = p_55059_.getBlockState(blockpos);
			BlockState blockstate1 = p_55059_.getBlockState(blockpos.above());

			if (blockstate1.isAir() && blockstate.getBlock() == Blocks.END_STONE) {
				p_55059_.setBlock(blockpos, ModBlocks.ENDERLAWN.get().defaultBlockState(), 3);
			}
			if (p_55060_.nextInt(5) == 0) {
				break;
			}
		}

	}
}
