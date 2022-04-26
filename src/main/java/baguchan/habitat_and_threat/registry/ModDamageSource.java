package baguchan.habitat_and_threat.registry;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.LivingEntity;

public class ModDamageSource {
	public static DamageSource explosionAttack(LivingEntity p_19371_) {
		return new EntityDamageSource("wildfire.explosion.attack", p_19371_).setExplosion();
	}

}
