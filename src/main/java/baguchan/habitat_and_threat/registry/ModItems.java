package baguchan.habitat_and_threat.registry;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.item.RawWildFireShieldItem;
import baguchan.habitat_and_threat.item.WildFireShieldItem;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HabitatAndThreat.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
	private static final ItemSubRegistryHelper HELPERS = HabitatAndThreat.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<ForgeSpawnEggItem> WILDFIRE_SPAWN_EGG = HELPERS.createSpawnEggItem("wildfire", ModEntities.WILDFIRE::get, 0xF9D731, 0x381007);

	public static final RegistryObject<WildFireShieldItem> WILDFIRE_SHIELD = HELPERS.createItem("wildfire_shield", () -> new WildFireShieldItem(new Item.Properties().stacksTo(1).durability(560).tab(CreativeModeTab.TAB_COMBAT)));

	public static final RegistryObject<RawWildFireShieldItem> RAW_WILDFIRE_SHIELD = HELPERS.createItem("raw_wildfire_shield", () -> new RawWildFireShieldItem(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MATERIALS)));

}
