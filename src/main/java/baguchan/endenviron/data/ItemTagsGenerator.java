package baguchan.endenviron.data;

import baguchan.endenviron.EndEnviron;
import baguchan.endenviron.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagsGenerator extends ItemTagsProvider {
	public ItemTagsGenerator(DataGenerator generator, BlockTagsProvider p_126531_, ExistingFileHelper exFileHelper) {
		super(generator, p_126531_, EndEnviron.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags() {
		tag(Tags.Items.END_STONES).add(ModBlocks.ENDERLAWN.get().asItem());
	}
}