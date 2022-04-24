package baguchan.habitat_and_threat.data;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagsGenerator extends BlockTagsProvider {
	public BlockTagsGenerator(DataGenerator generator, ExistingFileHelper exFileHelper) {
		super(generator, HabitatAndThreat.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags() {
		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ENDERLAWN.get(), ModBlocks.ENDERMITE_EGG.get(), ModBlocks.ENDERBITE_COCOON.get());
		tag(BlockTags.DRAGON_IMMUNE).add(ModBlocks.ENDERLAWN.get());
		tag(Tags.Blocks.END_STONES).add(ModBlocks.ENDERLAWN.get());
	}
}