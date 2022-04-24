package baguchan.habitat_and_threat.block;

import baguchan.habitat_and_threat.entity.Enderbite;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Random;

public class EndermiteEggBlock extends Block {
	private static final VoxelShape ONE_EGG_AABB = Block.box(3.0D, 0.0D, 3.0D, 12.0D, 7.0D, 12.0D);

	public static final IntegerProperty HATCH = BlockStateProperties.HATCH;

	public EndermiteEggBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, Integer.valueOf(0)));
	}

	public void stepOn(Level p_154857_, BlockPos p_154858_, BlockState p_154859_, Entity p_154860_) {
		this.destroyEgg(p_154857_, p_154859_, p_154858_, p_154860_, 200);
		super.stepOn(p_154857_, p_154858_, p_154859_, p_154860_);
	}

	public void fallOn(Level p_154845_, BlockState p_154846_, BlockPos p_154847_, Entity p_154848_, float p_154849_) {
		if (!(p_154848_ instanceof Zombie)) {
			this.destroyEgg(p_154845_, p_154846_, p_154847_, p_154848_, 50);
		}

		super.fallOn(p_154845_, p_154846_, p_154847_, p_154848_, p_154849_);
	}

	private void destroyEgg(Level p_154851_, BlockState p_154852_, BlockPos p_154853_, Entity p_154854_, int p_154855_) {
		if (this.canDestroyEgg(p_154851_, p_154854_)) {
			if (!p_154851_.isClientSide && p_154851_.random.nextInt(p_154855_) == 0) {
				this.decreaseEggs(p_154851_, p_154853_, p_154852_);
			}
		}
	}

	private void decreaseEggs(Level p_57792_, BlockPos p_57793_, BlockState p_57794_) {
		p_57792_.playSound((Player) null, p_57793_, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + p_57792_.random.nextFloat() * 0.2F);
		p_57792_.playSound((Player) null, p_57793_, SoundEvents.ENDERMITE_DEATH, SoundSource.BLOCKS, 1.0F, 0.9F + p_57792_.random.nextFloat() * 0.2F);

		p_57792_.destroyBlock(p_57793_, false);
	}

	public void randomTick(BlockState p_57804_, ServerLevel p_57805_, BlockPos p_57806_, Random p_57807_) {
		if (this.shouldUpdateHatchLevel(p_57805_) && onEndStone(p_57805_, p_57806_)) {
			int i = p_57804_.getValue(HATCH);
			if (i < 2) {
				p_57805_.playSound((Player) null, p_57806_, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + p_57807_.nextFloat() * 0.2F);
				p_57805_.setBlock(p_57806_, p_57804_.setValue(HATCH, Integer.valueOf(i + 1)), 2);
			} else {
				p_57805_.playSound((Player) null, p_57806_, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + p_57807_.nextFloat() * 0.2F);
				p_57805_.removeBlock(p_57806_, false);
				p_57805_.levelEvent(2001, p_57806_, Block.getId(p_57804_));
				Endermite endermite = EntityType.ENDERMITE.create(p_57805_);
				endermite.moveTo((double) p_57806_.getX() + 0.5D, (double) p_57806_.getY(), (double) p_57806_.getZ() + 0.5D, 0.0F, 0.0F);
				p_57805_.addFreshEntity(endermite);
			}
		}
	}

	public static boolean onEndStone(BlockGetter p_57763_, BlockPos p_57764_) {
		return isEndStone(p_57763_, p_57764_.below());
	}

	public static boolean isEndStone(BlockGetter p_57801_, BlockPos p_57802_) {
		return p_57801_.getBlockState(p_57802_).is(Tags.Blocks.END_STONES);
	}

	public void onPlace(BlockState p_57814_, Level p_57815_, BlockPos p_57816_, BlockState p_57817_, boolean p_57818_) {
		if (onEndStone(p_57815_, p_57816_) && !p_57815_.isClientSide) {
			p_57815_.levelEvent(2005, p_57816_, 0);
		}
	}

	private boolean shouldUpdateHatchLevel(Level p_57766_) {
		return p_57766_.random.nextInt(2) == 0;
	}

	public void playerDestroy(Level p_57771_, Player p_57772_, BlockPos p_57773_, BlockState p_57774_, @Nullable BlockEntity p_57775_, ItemStack p_57776_) {
		super.playerDestroy(p_57771_, p_57772_, p_57773_, p_57774_, p_57775_, p_57776_);
		this.decreaseEggs(p_57771_, p_57773_, p_57774_);
	}

	public VoxelShape getShape(BlockState p_57809_, BlockGetter p_57810_, BlockPos p_57811_, CollisionContext p_57812_) {
		return ONE_EGG_AABB;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_57799_) {
		p_57799_.add(HATCH);
	}


	private boolean canDestroyEgg(Level p_57768_, Entity p_57769_) {
		if (!(p_57769_ instanceof Enderbite) && !(p_57769_ instanceof Endermite) && !(p_57769_ instanceof Bat)) {
			if (!(p_57769_ instanceof LivingEntity)) {
				return false;
			} else {
				return p_57769_ instanceof Player || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(p_57768_, p_57769_);
			}
		} else {
			return false;
		}
	}
}
