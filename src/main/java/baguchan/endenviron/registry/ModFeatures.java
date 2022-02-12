package baguchan.endenviron.registry;

import baguchan.endenviron.EndEnviron;
import baguchan.endenviron.world.feature.EndVegetationFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EndEnviron.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures {
	public static final Feature<BlockStateConfiguration> END_VEGETATION = new EndVegetationFeature(BlockStateConfiguration.CODEC);

	@SubscribeEvent
	public static void registerFeature(RegistryEvent.Register<Feature<?>> event) {
		event.getRegistry().register(END_VEGETATION.setRegistryName("end_vegetation"));
	}
}