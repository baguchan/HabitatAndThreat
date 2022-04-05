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

public class EnderbiteModel<T extends Entity> extends EntityModel<T> {
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

	public EnderbiteModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.back = root.getChild("back");
		this.fetherL = root.getChild("fetherL");
		this.fetherR = root.getChild("fetherR");
		this.legR = root.getChild("legR");
		this.legR2 = root.getChild("legR2");
		this.legL = root.getChild("legL");
		this.legL2 = root.getChild("legL2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, -3.5F, 0.0F, 0.0942F, 0.0F));

		PartDefinition mouseL = head.addOrReplaceChild("mouseL", CubeListBuilder.create().texOffs(18, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 2.0F, -1.0F));

		PartDefinition mouseR = head.addOrReplaceChild("mouseR", CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 2.0F, -1.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 5).addBox(-3.0F, 0.0F, -2.5F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.14F, 19.0F, 0.0F));

		PartDefinition back = partdefinition.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, 0.0F, -0.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, 3.8F, -0.1955F, 0.0F, 0.0F));

		PartDefinition fetherL = partdefinition.addOrReplaceChild("fetherL", CubeListBuilder.create().texOffs(7, 0).addBox(0.0F, -0.01F, 0.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, -2.0F, 0.7037F, 0.3519F, 0.0F));

		PartDefinition fetherR = partdefinition.addOrReplaceChild("fetherR", CubeListBuilder.create().texOffs(7, 0).addBox(-3.0F, -0.01F, 0.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, -2.0F, 0.7037F, -0.3519F, 0.0F));

		PartDefinition legR = partdefinition.addOrReplaceChild("legR", CubeListBuilder.create().texOffs(0, 5).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 2.0F, -2.0F));

		PartDefinition legR2 = partdefinition.addOrReplaceChild("legR2", CubeListBuilder.create().texOffs(0, 5).addBox(-0.75F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 2.0F, 3.0F));

		PartDefinition legL = partdefinition.addOrReplaceChild("legL", CubeListBuilder.create().texOffs(0, 5).addBox(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 2.0F, -2.0F));

		PartDefinition legL2 = partdefinition.addOrReplaceChild("legL2", CubeListBuilder.create().texOffs(0, 5).addBox(1.0F, 0.0F, 4.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 2.0F, -2.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if(!entity.isOnGround()){
			this.fetherR.xRot = 0.7037F - (0.35F * Mth.cos(ageInTicks * 100F));
			this.fetherL.xRot = 0.7037F - (0.35F * Mth.cos(ageInTicks * 100F));
			this.fetherR.yRot = -0.3519F;
			this.fetherL.yRot = 0.3519F;
		}else {
			this.fetherR.xRot = 0.0F;
			this.fetherL.xRot = 0.0F;
			this.fetherR.yRot = 0.0F;
			this.fetherL.yRot = 0.0F;

			this.legR.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.legR2.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
			this.legL.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.legL2.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, buffer, packedLight, packedOverlay);
		body.render(poseStack, buffer, packedLight, packedOverlay);
		back.render(poseStack, buffer, packedLight, packedOverlay);
		fetherL.render(poseStack, buffer, packedLight, packedOverlay);
		fetherR.render(poseStack, buffer, packedLight, packedOverlay);
		legR.render(poseStack, buffer, packedLight, packedOverlay);
		legR2.render(poseStack, buffer, packedLight, packedOverlay);
		legL.render(poseStack, buffer, packedLight, packedOverlay);
		legL2.render(poseStack, buffer, packedLight, packedOverlay);
	}
}