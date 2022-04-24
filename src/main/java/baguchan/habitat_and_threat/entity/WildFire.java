package baguchan.habitat_and_threat.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class WildFire extends Blaze {
	private static final EntityDataAccessor<Float> DATA_SHIELD_HEALTH_ID = SynchedEntityData.defineId(WildFire.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Float> DATA_SHIELD_HEALTH_2_ID = SynchedEntityData.defineId(WildFire.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Float> DATA_SHIELD_HEALTH_3_ID = SynchedEntityData.defineId(WildFire.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Float> DATA_SHIELD_HEALTH_4_ID = SynchedEntityData.defineId(WildFire.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Float> DATA_SHIELD_ROTATION_ID = SynchedEntityData.defineId(WildFire.class, EntityDataSerializers.FLOAT);


	public WildFire(EntityType<? extends WildFire> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
		this.xpReward = 20;
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_SHIELD_HEALTH_ID, 20F);
		this.entityData.define(DATA_SHIELD_HEALTH_2_ID, 20F);
		this.entityData.define(DATA_SHIELD_HEALTH_3_ID, 20F);
		this.entityData.define(DATA_SHIELD_HEALTH_4_ID, 20F);
		this.entityData.define(DATA_SHIELD_ROTATION_ID, 0F);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.isAlive()) {
			setShieldRotation(calculateShieldVector(getShieldRotation() + 5F));
		}
	}

	public void setShieldHealth(float health, int id) {
		switch (id) {
			case 0 -> this.entityData.set(DATA_SHIELD_HEALTH_ID, health);
			case 1 -> this.entityData.set(DATA_SHIELD_HEALTH_2_ID, health);
			case 2 -> this.entityData.set(DATA_SHIELD_HEALTH_3_ID, health);
			case 3 -> this.entityData.set(DATA_SHIELD_HEALTH_4_ID, health);
			default -> this.entityData.set(DATA_SHIELD_HEALTH_ID, health);
		}
	}

	public float getShieldHealth(int id) {
		return switch (id) {
			case 0 -> this.entityData.get(DATA_SHIELD_HEALTH_ID);
			case 1 -> this.entityData.get(DATA_SHIELD_HEALTH_2_ID);
			case 2 -> this.entityData.get(DATA_SHIELD_HEALTH_3_ID);
			case 3 -> this.entityData.get(DATA_SHIELD_HEALTH_4_ID);
			default -> this.entityData.get(DATA_SHIELD_HEALTH_ID);
		};
	}

	public void setShieldRotation(float rotation) {
		this.entityData.set(DATA_SHIELD_ROTATION_ID, rotation);
	}

	public float getShieldRotation() {
		return this.entityData.get(DATA_SHIELD_ROTATION_ID);
	}

	@Override
	public boolean hurt(DamageSource p_21016_, float p_21017_) {
		Entity entity = p_21016_.getDirectEntity();
		boolean flag = false;

		if (entity instanceof AbstractArrow) {
			AbstractArrow abstractarrow = (AbstractArrow) entity;
			if (abstractarrow.getPierceLevel() > 0) {
				flag = true;
			}
		}

		for (int id = 0; id < 4; id++) {
			if (!p_21016_.isBypassArmor() && !flag) {
				Vec3 vec32 = p_21016_.getSourcePosition();
				if (vec32 != null) {
					Vec3 vec3 = this.getShieldVector(getShieldRotation() + id * 90.0F);
					Vec3 vec31 = vec32.vectorTo(this.position()).normalize();
					vec31 = new Vec3(vec31.x, 0.0D, vec31.z);
					if (vec31.dot(vec3) < 0.0D && this.getShieldHealth(id) > 0.0F) {
						this.setShieldHealth(getShieldHealth(id) - p_21017_, id);
						if (getShieldHealth(id) <= 0.0F) {
							this.playSound(SoundEvents.SHIELD_BREAK, 1.0F, 1.0F);
						}
						this.playSound(SoundEvents.SHIELD_BLOCK, 1.0F, 1.0F);
						return false;
					}
				}
			}
		}

		return super.hurt(p_21016_, p_21017_);
	}

	public Vec3 getShieldVector(float rotY) {
		return this.calculateViewVector(0.0F, this.calculateShieldVector(rotY));
	}

	public float calculateShieldVector(float rotY) {
		if (rotY > 360.0F) {
			return 0F;
		}

		if (rotY < 0) {
			return 360.0F;
		}
		return rotY;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag p_21484_) {
		super.addAdditionalSaveData(p_21484_);
		p_21484_.putFloat("ShieldHealth1", getShieldHealth(0));
		p_21484_.putFloat("ShieldHealth2", getShieldHealth(1));
		p_21484_.putFloat("ShieldHealth3", getShieldHealth(2));
		p_21484_.putFloat("ShieldHealth4", getShieldHealth(3));
		p_21484_.putFloat("ShieldRotation", getShieldRotation());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag p_21450_) {
		super.readAdditionalSaveData(p_21450_);
		this.setShieldHealth(p_21450_.getFloat("ShieldHealth1"), 0);
		this.setShieldHealth(p_21450_.getFloat("ShieldHealth2"), 1);
		this.setShieldHealth(p_21450_.getFloat("ShieldHealth3"), 2);
		this.setShieldHealth(p_21450_.getFloat("ShieldHealth4"), 3);
		this.setShieldRotation(p_21450_.getFloat("ShieldRotation"));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 7.0D).add(Attributes.MAX_HEALTH, 40.0F).add(Attributes.MOVEMENT_SPEED, (double) 0.23F).add(Attributes.FOLLOW_RANGE, 48.0D);
	}
}
