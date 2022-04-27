package baguchan.habitat_and_threat.registry;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.entity.Enderbite;
import baguchan.habitat_and_threat.entity.WildFire;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HabitatAndThreat.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, HabitatAndThreat.MODID);


	public static final RegistryObject<EntityType<Enderbite>> ENDERBITE = ENTITIES.register("enderbite", () -> EntityType.Builder.of(Enderbite::new, MobCategory.MONSTER).sized(0.6F, 0.5F).clientTrackingRange(8).build(HabitatAndThreat.MODID + ":enderbite"));
	public static final RegistryObject<EntityType<WildFire>> WILDFIRE = ENTITIES.register("wildfire", () -> EntityType.Builder.of(WildFire::new, MobCategory.MONSTER).sized(0.6F, 1.95F).fireImmune().clientTrackingRange(8).build(HabitatAndThreat.MODID + ":wildfire"));


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