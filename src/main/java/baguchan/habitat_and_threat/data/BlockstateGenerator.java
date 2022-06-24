package baguchan.habitat_and_threat.data;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class BlockstateGenerator extends BlockStateProvider {
	public BlockstateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, HabitatAndThreat.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.crossBlock(ModBlocks.END_GRASS.get());
		this.simpleBlock(ModBlocks.SLIMEND_MUD.get());
		this.simpleBlock(ModBlocks.PACKED_SLIMEND_MUD.get());
		this.simpleBlock(ModBlocks.SLIMEND_MUD_BRICK.get());
	}


	public void stairs(StairBlock block, Block fullBlock) {
		stairsBlock(block, texture(name(fullBlock)));
	}

	public void slab(SlabBlock block, Block fullBlock) {
		slabBlock(block, texture(name(fullBlock)), texture(name(fullBlock)));
	}

	public void crossBlock(Block block) {

		crossBlock(block, models().cross(name(block), texture(name(block))));
	}

	private void crossBlock(Block block, ModelFile model) {
		getVariantBuilder(block).forAllStates(state ->
				ConfiguredModel.builder()
						.modelFile(model)
						.build());
	}

	protected ResourceLocation texture(String name) {
		return modLoc("block/" + name);
	}

	protected String name(Block block) {
		return ForgeRegistries.BLOCKS.getKey(block).getPath();
	}

	@Nonnull
	@Override
	public String getName() {
		return "HabitatAndThreat blockstates and block models";
	}
}