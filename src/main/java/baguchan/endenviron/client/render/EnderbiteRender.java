package baguchan.endenviron.client.render;

import baguchan.endenviron.EndEnviron;
import baguchan.endenviron.client.ModModelLayers;
import baguchan.endenviron.client.model.EnderbiteModel;
import baguchan.endenviron.entity.Enderbite;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnderbiteRender<T extends Enderbite> extends MobRenderer<T, EnderbiteModel<T>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(EndEnviron.MODID, "textures/entity/enderbite/enderbite.png");

	public EnderbiteRender(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new EnderbiteModel<>(p_173952_.bakeLayer(ModModelLayers.ENDERBITE)), 0.35F);
	}


	@Override
	public ResourceLocation getTextureLocation(T p_110775_1_) {
		return TEXTURE;
	}
}