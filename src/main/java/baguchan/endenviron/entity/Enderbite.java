package baguchan.endenviron.entity;

import baguchan.endenviron.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class Enderbite extends Monster {
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

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 14.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.FLYING_SPEED, 0.24D).add(Attributes.ATTACK_DAMAGE, 4.0D);
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

	public boolean removeWhenFarAway(double p_35886_) {
		return false;
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
}
