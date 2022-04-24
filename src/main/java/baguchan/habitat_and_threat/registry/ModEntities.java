package baguchan.habitat_and_threat.registry;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.entity.Enderbite;
import baguchan.habitat_and_threat.entity.WildFire;
import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HabitatAndThreat.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
	private static final EntitySubRegistryHelper HELPERS = HabitatAndThreat.REGISTRY_HELPER.getEntitySubHelper();
	public static final RegistryObject<EntityType<Enderbite>> ENDERBITE = HELPERS.createLivingEntity("enderbite", Enderbite::new, MobCategory.MONSTER, 0.6F, 0.5F);
	public static final RegistryObject<EntityType<WildFire>> WILDFIRE = HELPERS.createLivingEntity("wildfire", WildFire::new, MobCategory.MONSTER, 0.6F, 1.95F);


	private static String prefix(String path) {
		return HabitatAndThreat.MODID + "." + path;
	}

	@SubscribeEvent
	public static void registerEntity(RegistryEvent.Register<EntityType<?>> event) {
		SpawnPlacements.register(ENDERBITE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Enderbite::checkEnderbiteSpawnRules);
		SpawnPlacements.register(WILDFIRE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules);

	}

	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
		event.put(ENDERBITE.get(), Enderbite.createAttributes().build());
		event.put(WILDFIRE.get(), WildFire.createAttributes().build());
	}
}