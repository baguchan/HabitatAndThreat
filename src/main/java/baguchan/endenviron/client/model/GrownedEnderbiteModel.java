package baguchan.endenviron.client.model;// Made with Blockbench 4.0.3
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class GrownedEnderbiteModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart back;
	private final ModelPart fetherL;
	private final ModelPart fetherR;
	private final ModelPart legR;
	private final ModelPart legR2;
	private final ModelPart legL;
	private final ModelPart legL2;

	public GrownedEnderbiteModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.back = this.body.getChild("back");
		this.fetherL = this.body.getChild("fetherL");
		this.fetherR = this.body.getChild("fetherR");
		this.legR = this.body.getChild("legR");
		this.legR2 = this.body.getChild("legR2");
		this.legL = this.body.getChild("legL");
		this.legL2 = this.body.getChild("legL2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -2.0F, -4.5F, 8.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition fetherL = body.addOrReplaceChild("fetherL", CubeListBuilder.create().texOffs(34, 0).addBox(0.0F, 0.01F, 0.0F, 5.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -2.0F, 0.7037F, 0.0F, 0.0F));

		PartDefinition fetherR = body.addOrReplaceChild("fetherR", CubeListBuilder.create().texOffs(0, 15).addBox(-5.0F, 0.01F, 0.0F, 5.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -2.0F, 0.7037F, 0.0F, 0.0F));

		PartDefinition legL2 = body.addOrReplaceChild("legL2", CubeListBuilder.create().texOffs(16, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 4.0F, 2.5F));

		PartDefinition legL = body.addOrReplaceChild("legL", CubeListBuilder.create().texOffs(28, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 4.0F, -2.5F));

		PartDefinition legR = body.addOrReplaceChild("legR", CubeListBuilder.create().texOffs(20, 28).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 4.0F, -2.5F));

		PartDefinition legR2 = body.addOrReplaceChild("legR2", CubeListBuilder.create().texOffs(20, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 4.0F, 2.5F));

		PartDefinition back = body.addOrReplaceChild("back", CubeListBuilder.create().texOffs(26, 15).addBox(-3.0F, 0.0F, -0.5F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.8F, -0.1955F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 24).addBox(-3.0F, -2.0F, -4.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.25F, -4.5F));

		PartDefinition mouseL = head.addOrReplaceChild("mouseL", CubeListBuilder.create().texOffs(8, 32).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 1.0F, -3.0F));

		PartDefinition mouseR = head.addOrReplaceChild("mouseR", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 1.0F, -3.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!entity.isOnGround()) {
			this.fetherR.xRot = 0.7037F - (0.35F * Mth.cos(ageInTicks * 100F));
			this.fetherL.xRot = 0.7037F - (0.35F * Mth.cos(ageInTicks * 100F));
			this.fetherR.yRot = -0.3519F;
			this.fetherL.yRot = 0.3519F;
		} else {
			this.fetherR.xRot = 0.0F;
			this.fetherL.xRot = 0.0F;
			this.fetherR.yRot = 0.0F;
			this.fetherL.yRot = 0.0F;

			this.legR.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.legR2.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
			this.legL.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.legL2.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, buffer, packedLight, packedOverlay);
	}
}