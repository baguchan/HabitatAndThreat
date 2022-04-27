package baguchan.habitat_and_threat;

import baguchan.habitat_and_threat.client.ClientRegistrar;
import baguchan.habitat_and_threat.registry.ModBiomes;
import baguchan.habitat_and_threat.registry.ModBlocks;
import baguchan.habitat_and_threat.registry.ModEntities;
import baguchan.habitat_and_threat.registry.ModItems;
import baguchan.habitat_and_threat.world.gen.ModPlacements;
import net.minecraft.resources.ResourceLocation;
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
@Mod(HabitatAndThreat.MODID)
public class HabitatAndThreat {
	public static final String MODID = "habitat_and_threat";
	public HabitatAndThreat() {
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		// Register the enqueueIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		// Register the processIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		ModBlocks.BLOCKS.register(bus);
		ModEntities.ENTITIES.register(bus);
		ModItems.ITEMS.register(bus);
		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientRegistrar::setup));
	}

	public static ResourceLocation prefix(String s) {
		return new ResourceLocation(HabitatAndThreat.MODID, s);
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
