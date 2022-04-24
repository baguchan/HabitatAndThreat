package baguchan.habitat_and_threat.client.render;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.client.ModModelLayers;
import baguchan.habitat_and_threat.client.model.WildFireModel;
import baguchan.habitat_and_threat.entity.WildFire;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WildFireRender extends MobRenderer<WildFire, EntityModel<WildFire>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(HabitatAndThreat.MODID, "textures/entity/wildfire.png");

	public WildFireRender(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new WildFireModel<>(p_173952_.bakeLayer(ModModelLayers.WILDFIRE)), 0.35F);
	}

	@Override
	public ResourceLocation getTextureLocation(WildFire p_110775_1_) {
		return TEXTURE;
	}
}