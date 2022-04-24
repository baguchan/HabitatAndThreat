package baguchan.habitat_and_threat.client.render;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.client.ModModelLayers;
import baguchan.habitat_and_threat.client.model.EnderbiteModel;
import baguchan.habitat_and_threat.entity.Enderbite;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnderbiteRender extends MobRenderer<Enderbite, EntityModel<Enderbite>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(HabitatAndThreat.MODID, "textures/entity/enderbite/enderbite.png");

	public EnderbiteRender(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new EnderbiteModel<>(p_173952_.bakeLayer(ModModelLayers.ENDERBITE)), 0.35F);
	}

	public void render(Enderbite p_115777_, float p_115778_, float p_115779_, PoseStack p_115780_, MultiBufferSource p_115781_, int p_115782_) {
		this.shadowRadius = 0.45F;
		super.render(p_115777_, p_115778_, p_115779_, p_115780_, p_115781_, p_115782_);
	}

	@Override
	public ResourceLocation getTextureLocation(Enderbite p_110775_1_) {
		return TEXTURE;
	}
}