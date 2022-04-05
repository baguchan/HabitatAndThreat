package baguchan.endenviron.client.render;

import baguchan.endenviron.EndEnviron;
import baguchan.endenviron.client.ModModelLayers;
import baguchan.endenviron.client.model.EnderbiteModel;
import baguchan.endenviron.client.model.GrownedEnderbiteModel;
import baguchan.endenviron.entity.Enderbite;
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
	private static final ResourceLocation TEXTURE = new ResourceLocation(EndEnviron.MODID, "textures/entity/enderbite/enderbite.png");
	private static final ResourceLocation ADULT_TEXTURE = new ResourceLocation(EndEnviron.MODID, "textures/entity/enderbite/grown_enderbite.png");


	private final EntityModel<Enderbite> adult;
	private final EntityModel<Enderbite> child;

	private boolean babyStateO = true;

	public EnderbiteRender(EntityRendererProvider.Context p_173952_) {
		super(p_173952_, new EnderbiteModel<>(p_173952_.bakeLayer(ModModelLayers.ENDERBITE)), 0.35F);
		this.adult = new GrownedEnderbiteModel<>(p_173952_.bakeLayer(ModModelLayers.GROWN_ENDERBITE));
		this.child = new EnderbiteModel<>(p_173952_.bakeLayer(ModModelLayers.ENDERBITE));
	}

	public void render(Enderbite p_115777_, float p_115778_, float p_115779_, PoseStack p_115780_, MultiBufferSource p_115781_, int p_115782_) {
		boolean baby = p_115777_.isBaby();
		if (baby != this.babyStateO) {
			if (baby) {
				this.model = this.child;
			} else {
				this.model = this.adult;
			}
		}

		this.babyStateO = baby;
		this.shadowRadius = 0.35F + (baby ? 0.0F : 0.2F);
		super.render(p_115777_, p_115778_, p_115779_, p_115780_, p_115781_, p_115782_);
	}

	@Override
	public ResourceLocation getTextureLocation(Enderbite p_110775_1_) {
		return p_110775_1_.isBaby() ? TEXTURE : ADULT_TEXTURE;
	}
}