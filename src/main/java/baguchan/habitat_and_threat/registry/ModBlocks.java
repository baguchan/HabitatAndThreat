package baguchan.habitat_and_threat.registry;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.block.EndGrassBlock;
import baguchan.habitat_and_threat.block.EnderbiteCocoonBlock;
import baguchan.habitat_and_threat.block.EndermiteEggBlock;
import baguchan.habitat_and_threat.block.EndlawnBlock;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HabitatAndThreat.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
	private static final BlockSubRegistryHelper HELPERS = HabitatAndThreat.REGISTRY_HELPER.getBlockSubHelper();
	public static final RegistryObject<Block> END_GRASS = HELPERS.createBlock("end_grass", () -> new EndGrassBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.SAND).sound(SoundType.GRASS).instabreak().noCollission()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ENDERLAWN = HELPERS.createBlock("enderlawn", () -> new EndlawnBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().randomTicks().sound(SoundType.NYLIUM).strength(3.5F, 9.5F)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ENDERBITE_COCOON = HELPERS.createBlock("enderbite_cocoon", () -> new EnderbiteCocoonBlock(BlockBehaviour.Properties.of(Material.EGG).sound(SoundType.WOOL).strength(0.45F, 0.75F).randomTicks()), CreativeModeTab.TAB_MATERIALS);
	public static final RegistryObject<Block> ENDERMITE_EGG = HELPERS.createBlock("endermite_egg", () -> new EndermiteEggBlock(BlockBehaviour.Properties.of(Material.EGG).sound(SoundType.METAL).strength(0.25F, 0.5F).randomTicks()), CreativeModeTab.TAB_MATERIALS);

}
