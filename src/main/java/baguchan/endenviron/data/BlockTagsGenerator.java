package baguchan.endenviron.data;

import baguchan.endenviron.EndEnviron;
import baguchan.endenviron.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagsGenerator extends BlockTagsProvider {
	public BlockTagsGenerator(DataGenerator generator, ExistingFileHelper exFileHelper) {
		super(generator, EndEnviron.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags() {
		tag(BlockTags.DRAGON_IMMUNE).add(ModBlocks.ENDERLAWN.get());
		tag(Tags.Blocks.END_STONES).add(ModBlocks.ENDERLAWN.get());
	}
}