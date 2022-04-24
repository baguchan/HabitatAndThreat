package baguchan.habitat_and_threat.client.model;// Made with Blockbench 4.2.2
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import baguchan.habitat_and_threat.entity.WildFire;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class WildFireModel<T extends WildFire> extends EntityModel<T> {
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart shield;
	private final ModelPart shield2;
	private final ModelPart shield3;
	private final ModelPart shield4;
	private final ModelPart helmet;

	public WildFireModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.shield = root.getChild("shield");
		this.shield2 = root.getChild("shield2");
		this.shield3 = root.getChild("shield3");
		this.shield4 = root.getChild("shield4");
		this.helmet = root.getChild("helmet");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 0.0F));

		PartDefinition shield = partdefinition.addOrReplaceChild("shield", CubeListBuilder.create().texOffs(16, 16).addBox(-5.0F, -1.0F, -7.0F, 10.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition shield2 = partdefinition.addOrReplaceChild("shield2", CubeListBuilder.create().texOffs(16, 16).addBox(-5.0F, -1.0F, -7.0F, 10.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, 0.0F, -0.2618F, 1.5708F, 0.0F));

		PartDefinition shield3 = partdefinition.addOrReplaceChild("shield3", CubeListBuilder.create().texOffs(16, 16).addBox(-5.0F, -1.0F, -7.0F, 10.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, 0.0F, -0.2618F, 3.1416F, 0.0F));

		PartDefinition shield4 = partdefinition.addOrReplaceChild("shield4", CubeListBuilder.create().texOffs(16, 16).addBox(-5.0F, -1.0F, -7.0F, 10.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, 0.0F, -0.2618F, -1.5708F, 0.0F));

		PartDefinition helmet = partdefinition.addOrReplaceChild("helmet", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(0.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);

		this.shield.yRot = entity.getShieldRotation() * ((float) Math.PI / 180F) + 0 * 1.5708F;
		this.shield2.yRot = entity.getShieldRotation() * ((float) Math.PI / 180F) + 1 * 1.5708F;
		this.shield3.yRot = entity.getShieldRotation() * ((float) Math.PI / 180F) + 2 * 1.5708F;
		this.shield4.yRot = entity.getShieldRotation() * ((float) Math.PI / 180F) + 3 * 1.5708F;

		this.shield.visible = entity.getShieldHealth(0) > 0.0F;
		this.shield2.visible = entity.getShieldHealth(1) > 0.0F;
		this.shield3.visible = entity.getShieldHealth(2) > 0.0F;
		this.shield4.visible = entity.getShieldHealth(3) > 0.0F;
		this.helmet.copyFrom(this.head);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, buffer, packedLight, packedOverlay);
		body.render(poseStack, buffer, packedLight, packedOverlay);
		shield.render(poseStack, buffer, packedLight, packedOverlay);
		shield2.render(poseStack, buffer, packedLight, packedOverlay);
		shield3.render(poseStack, buffer, packedLight, packedOverlay);
		shield4.render(poseStack, buffer, packedLight, packedOverlay);
		helmet.render(poseStack, buffer, packedLight, packedOverlay);
	}
}