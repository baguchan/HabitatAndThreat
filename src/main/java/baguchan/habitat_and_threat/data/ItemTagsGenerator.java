package baguchan.habitat_and_threat.data;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagsGenerator extends ItemTagsProvider {
	public ItemTagsGenerator(DataGenerator generator, BlockTagsProvider p_126531_, ExistingFileHelper exFileHelper) {
		super(generator, p_126531_, HabitatAndThreat.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags() {
		tag(Tags.Items.END_STONES).add(ModBlocks.ENDERLAWN.get().asItem());
	}
}