package baguchan.endenviron;

import baguchan.endenviron.client.ClientRegistrar;
import baguchan.endenviron.registry.ModBiomes;
import baguchan.endenviron.world.gen.ModPlacements;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EndEnviron.MODID)
public class EndEnviron {
	public static final String MODID = "endenviron";

	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

	public EndEnviron() {
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		// Register the enqueueIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		// Register the processIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		REGISTRY_HELPER.register(bus);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientRegistrar::setup));
	}

	private void setup(final FMLCommonSetupEvent event) {
		ModPlacements.init();
		ModBiomes.setupBiomeInfo();
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {
	}

	private void processIMC(final InterModProcessEvent event) {

	}
}
