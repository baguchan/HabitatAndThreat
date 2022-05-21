package baguchan.habitat_and_threat.entity;

import baguchan.habitat_and_threat.registry.ModDamageSource;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class WildFire extends Blaze {
	private static final EntityDataAccessor<Float> DATA_SHIELD_HEALTH_ID = SynchedEntityData.defineId(WildFire.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Float> DATA_SHIELD_HEALTH_2_ID = SynchedEntityData.defineId(WildFire.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Float> DATA_SHIELD_HEALTH_3_ID = SynchedEntityData.defineId(WildFire.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Float> DATA_SHIELD_HEALTH_4_ID = SynchedEntityData.defineId(WildFire.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Float> DATA_SHIELD_ROTATION_ID = SynchedEntityData.defineId(WildFire.class, EntityDataSerializers.FLOAT);

	private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(WildFire.class, EntityDataSerializers.BYTE);

	private float shieldSpeed = 1.0F;

	private static final int FLAG_CHARGED = 1;
	public static final int FLAG_FLAMING = 4;
	public static final int FLAG_SHIELD_BASH = 8;

	public WildFire(EntityType<? extends WildFire> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
		this.xpReward = 20;
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(4, new WildFireAttackGoal(this));
		this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(Blaze.class));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_SHIELD_HEALTH_ID, 20F);
		this.entityData.define(DATA_SHIELD_HEALTH_2_ID, 20F);
		this.entityData.define(DATA_SHIELD_HEALTH_3_ID, 20F);
		this.entityData.define(DATA_SHIELD_HEALTH_4_ID, 20F);
		this.entityData.define(DATA_SHIELD_ROTATION_ID, 0F);
		this.entityData.define(DATA_FLAGS_ID, (byte) 0);
	}


	private boolean getFlag(int p_29219_) {
		return (this.entityData.get(DATA_FLAGS_ID) & p_29219_) != 0;
	}

	private void setFlag(int p_29135_, boolean p_29136_) {
		byte b0 = this.entityData.get(DATA_FLAGS_ID);
		if (p_29136_) {
			this.entityData.set(DATA_FLAGS_ID, (byte) (b0 | p_29135_));
		} else {
			this.entityData.set(DATA_FLAGS_ID, (byte) (b0 & ~p_29135_));
		}

	}

	public boolean isCharged() {
		return this.getFlag(1);
	}

	public void setCharged(boolean p_28611_) {
		this.setFlag(1, p_28611_);
	}

	public boolean isFlaming() {
		return this.getFlag(4);
	}

	public void setFlaming(boolean p_28611_) {
		this.setFlag(4, p_28611_);
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
	public void tick() {
		super.tick();
		if (this.isAlive()) {
			setShieldRotation(calculateShieldVector(getShieldRotation() + (5F * this.shieldSpeed)));
		}

		if (this.isFlaming()) {
			this.level.broadcastEntityEvent(this, (byte) 45);
		}

		if (this.isFreezing()) {
			this.shieldSpeed = Mth.clamp(this.shieldSpeed - 0.05F, 0.5F, 3);
		} else if (this.isFlaming()) {
			this.shieldSpeed = Mth.clamp(this.shieldSpeed + 0.1F, 0.5F, 3);
		} else {
			if (this.shieldSpeed > 1.0F) {
				this.shieldSpeed = Mth.clamp(this.shieldSpeed - 0.05F, 0.5F, 3);
			} else if (this.shieldSpeed < 1.0F && this.isCharged()) {
				this.shieldSpeed = Mth.clamp(this.shieldSpeed + 0.05F, 0.5F, 3);
			}
		}
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
		return Monster.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 7.0D).add(Attributes.MAX_HEALTH, 60.0F).add(Attributes.MOVEMENT_SPEED, (double) 0.23F).add(Attributes.FOLLOW_RANGE, 48.0D);
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
			if (!p_21016_.isBypassArmor() && !p_21016_.isFire() && !flag) {
				Vec3 vec32 = p_21016_.getSourcePosition();
				if (vec32 != null) {
					Vec3 vec3 = this.getShieldAndViewVector(getShieldRotation() + id * 90.0F);
					Vec3 vec31 = vec32.vectorTo(this.position()).normalize();
					vec31 = new Vec3(vec31.x, 0.0D, vec31.z);
					if (vec31.dot(vec3) < -0.35D && this.getShieldHealth(id) > 0.0F) {
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

	public Vec3 getShieldAndViewVector(float rotY) {
		return this.calculateViewVector(0.0F, (this.getYRot() + rotY) % 360);
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

	public boolean isOnFire() {
		return this.isCharged();
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}

	public void handleEntityEvent(byte p_28456_) {
		if (p_28456_ == 45) {
			for (int i = 0; i < 8; ++i) {
				this.level.addParticle(ParticleTypes.FLAME, this.getX() + (this.random.nextFloat() - this.random.nextFloat()) * 4.5F, this.getY() + (this.random.nextFloat() - this.random.nextFloat()) * 4.5F, this.getZ() + (this.random.nextFloat() - this.random.nextFloat()) * 4.5F, 0.0F, 0.0F, 0.0F);
			}
		} else if (p_28456_ == 46) {
			for (int i = 0; i < 12; ++i) {
				this.level.addParticle(ParticleTypes.EXPLOSION, this.getX() + (this.random.nextFloat() - this.random.nextFloat()) * 4.5F, this.getY() + (this.random.nextFloat() - this.random.nextFloat()) * 4.5F, this.getZ() + (this.random.nextFloat() - this.random.nextFloat()) * 4.5F, 0.2F, 0.0F, 0.0F);
			}
		} else {
			super.handleEntityEvent(p_28456_);
		}

	}

	static class WildFireAttackGoal extends Goal {
		private final WildFire blaze;
		private int attackStep;
		private int attackTime;
		private int stompTime;
		private int stompCooldown;
		private int lastSeen;

		public WildFireAttackGoal(WildFire p_32247_) {
			this.blaze = p_32247_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		public boolean canUse() {
			LivingEntity livingentity = this.blaze.getTarget();
			return livingentity != null && livingentity.isAlive() && this.blaze.canAttack(livingentity);
		}

		public void start() {
			this.attackStep = 0;
			this.stompTime = 240;
			this.stompCooldown = 0;
		}

		public void stop() {
			this.blaze.setCharged(false);
			this.blaze.setFlaming(false);
			this.lastSeen = 0;
		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		public void tick() {
			if (this.stompCooldown > 0) {
				--this.stompCooldown;
			}

			--this.attackTime;
			LivingEntity livingentity = this.blaze.getTarget();
			if (livingentity != null) {
				boolean flag = this.blaze.getSensing().hasLineOfSight(livingentity);
				if (flag) {
					this.lastSeen = 0;
				} else {
					++this.lastSeen;
				}

				double d0 = this.blaze.distanceToSqr(livingentity);

				if ((!this.blaze.isInWater() || !this.blaze.isFreezing()) && (d0 < 24.0D || this.blaze.isFlaming()) && this.stompCooldown <= 0) {

					if (this.stompTime == 80) {
						this.blaze.setFlaming(true);
					}

					if (this.stompTime == 20) {
						if (!this.blaze.isSilent()) {
							this.blaze.playSound(SoundEvents.FIRE_EXTINGUISH, 2.0F, 0.8F);
							this.blaze.playSound(SoundEvents.GENERIC_EXPLODE, 2.0F, 0.8F);
						}

						this.blaze.setFlaming(false);

						for (LivingEntity entity : this.blaze.level.getEntitiesOfClass(LivingEntity.class, this.blaze.getBoundingBox().inflate(3.5F))) {
							if (this.blaze != entity && !entity.fireImmune()) {
								boolean flag2 = entity.hurt(ModDamageSource.explosionAttack(blaze), 10.0F);
								entity.knockback(flag2 ? 0.6F : 0.3F, (double) Mth.sin(this.blaze.getYRot() * ((float) Math.PI / 180F)), (double) (-Mth.cos(this.blaze.getYRot() * ((float) Math.PI / 180F))));
							}
						}
						this.blaze.level.broadcastEntityEvent(this.blaze, (byte) 46);
					}


					if (this.stompTime <= 0) {
						this.stompCooldown = 400;
					} else {
						--this.stompTime;

					}

					this.blaze.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 0.8D);
				} else if (d0 < 4.0D) {
					if (!flag) {
						return;
					}
					this.stompTime = 240;

					if (this.attackTime <= 0) {
						this.attackTime = 20;
						this.blaze.doHurtTarget(livingentity);
						this.stompCooldown = 0;
					}

					this.blaze.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
					this.blaze.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
				} else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
					double d1 = livingentity.getX() - this.blaze.getX();
					double d2 = livingentity.getY(0.5D) - this.blaze.getY(0.5D);
					double d3 = livingentity.getZ() - this.blaze.getZ();
					if (this.attackTime <= 0) {
						++this.attackStep;
						if (this.attackStep == 1) {
							this.attackTime = 60;
							this.blaze.setCharged(true);
						} else if (this.attackStep <= 5) {
							this.attackTime = 6;
						} else {
							this.attackTime = 100;
							this.attackStep = 0;
							this.blaze.setCharged(false);
						}

						if (this.attackStep > 1) {
							double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;
							if (!this.blaze.isSilent()) {
								this.blaze.level.levelEvent((Player) null, 1018, this.blaze.blockPosition(), 0);
							}

							for (int i = 0; i < 1; ++i) {
								SmallFireball smallfireball = new SmallFireball(this.blaze.level, this.blaze, d1 + this.blaze.getRandom().nextGaussian() * d4, d2, d3 + this.blaze.getRandom().nextGaussian() * d4);
								smallfireball.setPos(smallfireball.getX(), this.blaze.getY(0.5D) + 0.5D, smallfireball.getZ());
								this.blaze.level.addFreshEntity(smallfireball);
							}
						}
					}

					this.stompTime = 100;

					this.blaze.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
				} else if (this.lastSeen < 5) {
					this.stompTime = 100;
					this.blaze.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
				}

				super.tick();
			}
		}

		private double getFollowDistance() {
			return this.blaze.getAttributeValue(Attributes.FOLLOW_RANGE);
		}
	}
}
