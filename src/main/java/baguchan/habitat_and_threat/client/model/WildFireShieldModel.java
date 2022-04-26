package baguchan.habitat_and_threat.client.model;// Made with Blockbench 4.2.3
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class WildFireShieldModel<T extends Entity> extends EntityModel<T> {
	private final ModelPart shield;

	public WildFireShieldModel(ModelPart root) {
		this.shield = root.getChild("shield");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition shield = partdefinition.addOrReplaceChild("shield", CubeListBuilder.create().texOffs(16, 16).addBox(-5.0F, -8.0F, -2.0F, 10.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition handle = shield.addOrReplaceChild("handle", CubeListBuilder.create().texOffs(0, 38).addBox(-0.5F, -2.5F, 0.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		shield.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}