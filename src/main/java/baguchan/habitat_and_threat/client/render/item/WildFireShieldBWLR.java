package baguchan.habitat_and_threat.client.render.item;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.client.ModModelLayers;
import baguchan.habitat_and_threat.client.model.WildFireShieldModel;
import baguchan.habitat_and_threat.registry.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class WildFireShieldBWLR extends BlockEntityWithoutLevelRenderer {
	public static final Material SHIELD = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(HabitatAndThreat.MODID, "entity/wildfire_shield"));
	public static final Material RAW_SHIELD = new Material(Sheets.SHIELD_SHEET, new ResourceLocation(HabitatAndThreat.MODID, "entity/raw_wildfire_shield"));


	private WildFireShieldModel shieldModel;

	public WildFireShieldBWLR() {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
		this.shieldModel = new WildFireShieldModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.WILDFIRE_SHIELD));
	}

	@Override
	public void renderByItem(ItemStack pStack, ItemTransforms.TransformType pTransformType, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pOverlay) {
		if (pStack.is(ModItems.WILDFIRE_SHIELD.get())) {
			pPoseStack.pushPose();
			pPoseStack.scale(1.0F, -1.0F, -1.0F);
			Material material = SHIELD;
			VertexConsumer vertexconsumer = material.sprite().wrap(ItemRenderer.getFoilBufferDirect(pBuffer, this.shieldModel.renderType(material.atlasLocation()), true, pStack.hasFoil()));
			this.shieldModel.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, pOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

			pPoseStack.popPose();
		} else if (pStack.is(ModItems.RAW_WILDFIRE_SHIELD.get())) {
			pPoseStack.pushPose();
			pPoseStack.scale(1.0F, -1.0F, -1.0F);
			Material material = RAW_SHIELD;
			VertexConsumer vertexconsumer = material.sprite().wrap(ItemRenderer.getFoilBufferDirect(pBuffer, this.shieldModel.renderType(material.atlasLocation()), true, pStack.hasFoil()));
			this.shieldModel.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, pOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

			pPoseStack.popPose();
		}
	}
}
