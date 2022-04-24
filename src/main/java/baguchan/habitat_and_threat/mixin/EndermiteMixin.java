package baguchan.habitat_and_threat.mixin;

import baguchan.habitat_and_threat.registry.ModBlocks;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Endermite.class)
public abstract class EndermiteMixin extends Monster {

	private int growing;

	protected EndermiteMixin(EntityType<? extends Monster> p_33002_, Level p_33003_) {
		super(p_33002_, p_33003_);
	}

	public void readAdditionalSaveData(CompoundTag p_32595_) {
		super.readAdditionalSaveData(p_32595_);
		this.growing = p_32595_.getInt("Growing");
	}

	public void addAdditionalSaveData(CompoundTag p_32610_) {
		super.addAdditionalSaveData(p_32610_);
		p_32610_.putInt("Growing", this.growing);
	}

	/**
	 * This change is make process to grow endermite instead of despawning
	 */
	@Inject(method = "aiStep", at = @At("HEAD"), cancellable = true)
	public void aiStep(CallbackInfo callbackInfo) {
		super.aiStep();
		if (this.level.isClientSide) {
			for(int i = 0; i < 2; ++i) {
				this.level.addParticle(ParticleTypes.PORTAL, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
			}
		} else {
			++this.growing;

			if (this.growing >= 2400 && this.isAlive()) {
				this.convertToEnderbite();
			}
		}

		callbackInfo.cancel();
	}

	protected void convertToEnderbite() {
		this.level.setBlock(this.blockPosition(), ModBlocks.ENDERBITE_COCOON.get().defaultBlockState(), 3);
		this.playSound(SoundEvents.TURTLE_EGG_CRACK, 1.0F, 1.0F);
	}
}
