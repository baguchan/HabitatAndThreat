package baguchan.habitat_and_threat.registry;

import baguchan.habitat_and_threat.HabitatAndThreat;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HabitatAndThreat.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
	private static final ItemSubRegistryHelper HELPERS = HabitatAndThreat.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<ForgeSpawnEggItem> WILDFIRE_SPAWN_EGG = HELPERS.createSpawnEggItem("wildfire", ModEntities.WILDFIRE::get, 0xF9D731, 0x381007);

}
