package baguchan.habitat_and_threat.registry;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.item.RawWildFireShieldItem;
import baguchan.habitat_and_threat.item.WildFireShieldItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HabitatAndThreat.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HabitatAndThreat.MODID);

	public static final RegistryObject<ForgeSpawnEggItem> WILDFIRE_SPAWN_EGG = ITEMS.register("wildfire_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.WILDFIRE, 0xF9D731, 0x381007, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

	public static final RegistryObject<WildFireShieldItem> WILDFIRE_SHIELD = ITEMS.register("wildfire_shield", () -> new WildFireShieldItem(new Item.Properties().stacksTo(1).durability(560).tab(CreativeModeTab.TAB_COMBAT)));

	public static final RegistryObject<RawWildFireShieldItem> RAW_WILDFIRE_SHIELD = ITEMS.register("raw_wildfire_shield", () -> new RawWildFireShieldItem(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MATERIALS)));

}
