package baguchan.habitat_and_threat.client;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.client.model.EnderbiteModel;
import baguchan.habitat_and_threat.client.render.EnderbiteRender;
import baguchan.habitat_and_threat.registry.ModBlocks;
import baguchan.habitat_and_threat.registry.ModEntities;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = HabitatAndThreat.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistrar {
	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntities.ENDERBITE.get(), EnderbiteRender::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModModelLayers.ENDERBITE, EnderbiteModel::createBodyLayer);
	}

	public static void renderBlockLayer() {
		setRenderLayer(ModBlocks.ENDERLAWN.get(), RenderType.cutoutMipped());
		setRenderLayer(ModBlocks.END_GRASS.get(), RenderType.cutout());
	}

	private static void setRenderLayer(Block block, RenderType type) {
		ItemBlockRenderTypes.setRenderLayer(block, type::equals);
	}

	public static void setup(FMLCommonSetupEvent event) {
		renderBlockLayer();
	}
}
