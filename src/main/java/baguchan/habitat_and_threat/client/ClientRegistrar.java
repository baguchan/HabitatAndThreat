package baguchan.habitat_and_threat.client;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.client.model.EnderbiteModel;
import baguchan.habitat_and_threat.client.model.WildFireModel;
import baguchan.habitat_and_threat.client.model.WildFireShieldModel;
import baguchan.habitat_and_threat.client.render.EnderbiteRender;
import baguchan.habitat_and_threat.client.render.WildFireRender;
import baguchan.habitat_and_threat.client.render.item.WildFireShieldBWLR;
import baguchan.habitat_and_threat.registry.ModBlocks;
import baguchan.habitat_and_threat.registry.ModEntities;
import baguchan.habitat_and_threat.registry.ModItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = HabitatAndThreat.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistrar {
	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntities.ENDERBITE.get(), EnderbiteRender::new);
		event.registerEntityRenderer(ModEntities.WILDFIRE.get(), WildFireRender::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModModelLayers.ENDERBITE, EnderbiteModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.WILDFIRE, WildFireModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.WILDFIRE_SHIELD, WildFireShieldModel::createBodyLayer);
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
		ItemProperties.register(ModItems.WILDFIRE_SHIELD.get(), new ResourceLocation("blocking"), (p_174590_, p_174591_, p_174592_, p_174593_) -> {
			return p_174592_ != null && p_174592_.isUsingItem() && p_174592_.getUseItem() == p_174590_ ? 1.0F : 0.0F;
		});
	}

	@SubscribeEvent
	public static void onTextureStitch(TextureStitchEvent.Pre event) {
		if (event.getAtlas().location().equals(Sheets.SHIELD_SHEET)) {
			event.addSprite(WildFireShieldBWLR.SHIELD.texture());
			event.addSprite(WildFireShieldBWLR.RAW_SHIELD.texture());
		}
	}
}
