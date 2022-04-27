package baguchan.habitat_and_threat.registry;

import baguchan.habitat_and_threat.HabitatAndThreat;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.LivingEntity;

public class ModDamageSource {
	public static DamageSource explosionAttack(LivingEntity p_19371_) {
		return new EntityDamageSource(HabitatAndThreat.MODID + ".wildfire.explosion", p_19371_).setExplosion();
	}

	public static DamageSource wildfireShieldAttack(LivingEntity p_19371_) {
		return new EntityDamageSource(HabitatAndThreat.MODID + ".wildfire_sheild", p_19371_).setIsFire();
	}

}
