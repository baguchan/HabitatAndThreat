package baguchan.habitat_and_threat.data;

import baguchan.habitat_and_threat.HabitatAndThreat;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = HabitatAndThreat.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent evt) {
		evt.getGenerator().addProvider(evt.includeServer(), new BlockstateGenerator(evt.getGenerator(), evt.getExistingFileHelper()));
		evt.getGenerator().addProvider(evt.includeServer(), new ItemModelGenerator(evt.getGenerator(), evt.getExistingFileHelper()));
		BlockTagsProvider blocktags = new BlockTagsGenerator(evt.getGenerator(), evt.getExistingFileHelper());
		evt.getGenerator().addProvider(evt.includeServer(), blocktags);
		evt.getGenerator().addProvider(evt.includeServer(), new BiomeTagsGenerator(evt.getGenerator(), evt.getExistingFileHelper()));
		evt.getGenerator().addProvider(evt.includeServer(), new ItemTagsGenerator(evt.getGenerator(), blocktags, evt.getExistingFileHelper()));
		evt.getGenerator().addProvider(evt.includeServer(), new LootGenerator(evt.getGenerator()));
		evt.getGenerator().addProvider(evt.includeServer(), new CraftingGenerator(evt.getGenerator()));
	}
}