package baguchan.habitat_and_threat.data;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.registry.ModBiomes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BiomeTagsGenerator extends BiomeTagsProvider {
	public BiomeTagsGenerator(DataGenerator generator, ExistingFileHelper exFileHelper) {
		super(generator, HabitatAndThreat.MODID, exFileHelper);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTags() {
		tag(Tags.Biomes.IS_END).add(ModBiomes.END_WILD.getKey());
	}
}