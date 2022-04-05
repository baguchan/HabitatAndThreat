package baguchan.endenviron.entity;

import baguchan.endenviron.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Random;

public class Enderbite extends Monster {
	public static final EntityDimensions ADULT_DIMENSIONS = EntityDimensions.scalable(0.8F, 0.5F);
	public static final EntityDimensions DIMENSIONS = EntityDimensions.scalable(0.5F, 0.4F);


	private static final EntityDataAccessor<Boolean> DATA_BABY_ID = SynchedEntityData.defineId(Enderbite.class, EntityDataSerializers.BOOLEAN);
	public static final int BABY_START_AGE = -24000;
	private static final int FORCED_AGE_PARTICLE_TICKS = 40;
	protected int age;
	protected int forcedAge;
	protected int forcedAgeTimer;

	public Enderbite(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
		this.moveControl = new FlyingMoveControl(this, 10, false);
		this.xpReward = 4;
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_BABY_ID, false);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.FLYING_SPEED, 0.24D).add(Attributes.ATTACK_DAMAGE, 4.0D);
	}

	protected PathNavigation createNavigation(Level p_29417_) {
		FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_29417_);
		flyingpathnavigation.setCanOpenDoors(false);
		flyingpathnavigation.setCanFloat(true);
		flyingpathnavigation.setCanPassDoors(true);
		return flyingpathnavigation;
	}

	protected MovementEmission getMovementEmission() {
		return MovementEmission.EVENTS;
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENDERMITE_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource p_32615_) {
		return SoundEvents.ENDERMITE_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENDERMITE_DEATH;
	}

	protected void playStepSound(BlockPos p_32607_, BlockState p_32608_) {
		this.playSound(SoundEvents.ENDERMITE_STEP, 0.15F, 1.0F);
	}

	public void tick() {
		super.tick();
	}

	public double getMyRidingOffset() {
		return 0.12D;
	}

	public void aiStep() {
		super.aiStep();
		if (this.level.isClientSide) {
			for (int i = 0; i < 2; ++i) {
				this.level.addParticle(ParticleTypes.PORTAL, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
			}
		}

		Vec3 vec3 = this.getDeltaMovement();
		if (!this.onGround && vec3.y < 0.0D) {
			this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
		}

		if (this.level.isClientSide) {
			if (this.forcedAgeTimer > 0) {
				if (this.forcedAgeTimer % 4 == 0) {
					this.level.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), 0.0D, 0.0D, 0.0D);
				}

				--this.forcedAgeTimer;
			}
		} else if (this.isAlive()) {
			int i = this.getAge();
			if (i < 0) {
				++i;
				this.setAge(i);
			} else if (i > 0) {
				--i;
				this.setAge(i);
			}
		}
	}

	public static boolean checkEnderbiteSpawnRules(EntityType<Enderbite> p_29424_, LevelAccessor p_29425_, MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
		if (checkAnyLightMonsterSpawnRules(p_29424_, p_29425_, p_29426_, p_29427_, p_29428_)) {
			BlockState blockstate = p_29425_.getBlockState(p_29427_.below());
			return blockstate.is(Blocks.END_STONE) || blockstate.is(ModBlocks.ENDERLAWN.get());
		} else {
			return false;
		}

	}

	@Override
	public void travel(Vec3 p_21280_) {
		float normalFlyingSpeed = this.flyingSpeed;
		this.flyingSpeed = this.onGround ? normalFlyingSpeed : this.getSpeed() * (0.21600002F);
		super.travel(p_21280_);
		this.flyingSpeed = normalFlyingSpeed;
	}

	public MobType getMobType() {
		return MobType.ARTHROPOD;
	}


	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}

	public boolean removeWhenFarAway(double p_149183_) {
		return !this.hasCustomName();
	}

	public boolean causeFallDamage(float p_148989_, float p_148990_, DamageSource p_148991_) {
		return false;
	}

	protected void checkFallDamage(double p_29370_, boolean p_29371_, BlockState p_29372_, BlockPos p_29373_) {
	}

	@Override
	public int getMaxFallDistance() {
		return this.isOnGround() ? super.getMaxFallDistance() : 14;
	}

	@Override
	public boolean canAttack(LivingEntity p_21171_) {
		return p_21171_.getType() == EntityType.ENDERMITE ? false : super.canAttack(p_21171_);
	}


	public int getAge() {
		if (this.level.isClientSide) {
			return this.entityData.get(DATA_BABY_ID) ? -1 : 1;
		} else {
			return this.age;
		}
	}

	public void ageUp(int p_146741_, boolean p_146742_) {
		int i = this.getAge();
		i += p_146741_ * 20;
		if (i > 0) {
			i = 0;
		}

		int j = i - i;
		this.setAge(i);
		if (p_146742_) {
			this.forcedAge += j;
			if (this.forcedAgeTimer == 0) {
				this.forcedAgeTimer = 40;
			}
		}

		if (this.getAge() == 0) {
			this.setAge(this.forcedAge);
		}

	}

	public void ageUp(int p_146759_) {
		this.ageUp(p_146759_, false);
	}

	public void setAge(int p_146763_) {
		int i = this.age;
		this.age = p_146763_;
		if (i < 0 && p_146763_ >= 0 || i >= 0 && p_146763_ < 0) {
			this.entityData.set(DATA_BABY_ID, p_146763_ < 0);
			this.ageBoundaryReached();
		}

	}

	public void addAdditionalSaveData(CompoundTag p_146761_) {
		super.addAdditionalSaveData(p_146761_);
		p_146761_.putInt("Age", this.getAge());
		p_146761_.putInt("ForcedAge", this.forcedAge);
	}

	public void readAdditionalSaveData(CompoundTag p_146752_) {
		super.readAdditionalSaveData(p_146752_);
		this.setAge(p_146752_.getInt("Age"));
		this.forcedAge = p_146752_.getInt("ForcedAge");
	}

	public void onSyncedDataUpdated(EntityDataAccessor<?> p_146754_) {
		if (DATA_BABY_ID.equals(p_146754_)) {
			this.refreshDimensions();
		}

		super.onSyncedDataUpdated(p_146754_);
	}

	protected void ageBoundaryReached() {
	}

	public boolean isBaby() {
		return this.getAge() < 0;
	}

	public void setBaby(boolean p_146756_) {
		this.setAge(p_146756_ ? -24000 : 0);
	}

	@Override
	public EntityDimensions getDimensions(Pose p_21047_) {
		return p_21047_ == Pose.SLEEPING ? SLEEPING_DIMENSIONS : isBaby() ? DIMENSIONS : ADULT_DIMENSIONS;
	}

	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_, MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_, @Nullable CompoundTag p_146750_) {
		if (p_146749_ == null) {
			p_146749_ = new AgeableMob.AgeableMobGroupData(true);
		}

		AgeableMob.AgeableMobGroupData ageablemob$ageablemobgroupdata = (AgeableMob.AgeableMobGroupData) p_146749_;
		if (ageablemob$ageablemobgroupdata.isShouldSpawnBaby() && ageablemob$ageablemobgroupdata.getGroupSize() > 0 && this.random.nextFloat() <= ageablemob$ageablemobgroupdata.getBabySpawnChance()) {
			this.setAge(-24000);
		}

		ageablemob$ageablemobgroupdata.increaseGroupSizeByOne();
		return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
	}
}
