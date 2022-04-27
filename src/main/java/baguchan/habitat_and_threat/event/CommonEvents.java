package baguchan.habitat_and_threat.event;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.registry.ModDamageSource;
import baguchan.habitat_and_threat.registry.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HabitatAndThreat.MODID)
public class CommonEvents {

	@SubscribeEvent
	public static void shieldEvent(ShieldBlockEvent event) {
		Entity entity = event.getDamageSource().getDirectEntity();

		if (entity != null && event.getEntityLiving().getItemInHand(event.getEntityLiving().getUsedItemHand()).getItem() == ModItems.WILDFIRE_SHIELD.get()) {
			if (!event.getDamageSource().isExplosion()) {
				entity.hurt(ModDamageSource.wildfireShieldAttack(event.getEntityLiving()), 4.0F);
				entity.setSecondsOnFire(8);
			}
		}
	}
}
