package baguchan.endenviron.registry;

import baguchan.endenviron.EndEnviron;
import baguchan.endenviron.block.EndGrassBlock;
import baguchan.endenviron.block.EndlawnBlock;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = EndEnviron.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
	private static final BlockSubRegistryHelper HELPERS = EndEnviron.REGISTRY_HELPER.getBlockSubHelper();
	public static final RegistryObject<Block> END_GRASS = HELPERS.createBlock("end_grass", () -> new EndGrassBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.SAND).sound(SoundType.GRASS).instabreak().noCollission()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ENDERLAWN = HELPERS.createBlock("enderlawn", () -> new EndlawnBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().randomTicks().sound(SoundType.NYLIUM).strength(3.5F, 9.5F)), CreativeModeTab.TAB_BUILDING_BLOCKS);
}
