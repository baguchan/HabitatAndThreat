package baguchan.endenviron.registry;

import baguchan.endenviron.EndEnviron;
import baguchan.endenviron.entity.Enderbite;
import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = EndEnviron.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
	private static final EntitySubRegistryHelper HELPERS = EndEnviron.REGISTRY_HELPER.getEntitySubHelper();
	public static final RegistryObject<EntityType<Enderbite>> ENDERBITE = HELPERS.createLivingEntity("enderbite", Enderbite::new, MobCategory.CREATURE, 0.5F, 0.4F);

	private static String prefix(String path) {
		return EndEnviron.MODID + "." + path;
	}

	@SubscribeEvent
	public static void registerEntity(RegistryEvent.Register<EntityType<?>> event) {
		SpawnPlacements.register(ENDERBITE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Enderbite::checkEnderbiteSpawnRules);
	}

	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
		event.put(ENDERBITE.get(), Enderbite.createAttributes().build());
	}
}