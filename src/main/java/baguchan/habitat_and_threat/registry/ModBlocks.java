package baguchan.habitat_and_threat.registry;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.block.EndGrassBlock;
import baguchan.habitat_and_threat.block.EnderbiteCocoonBlock;
import baguchan.habitat_and_threat.block.EndermiteEggBlock;
import baguchan.habitat_and_threat.block.EndlawnBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = HabitatAndThreat.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HabitatAndThreat.MODID);


	public static final RegistryObject<LiquidBlock> SLIMEND = noItemRegister("slimend", () -> new LiquidBlock(ModFluids.SLIMEND, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100F).noLootTable()));

	public static final RegistryObject<Block> SLIMEND_MUD = register("slimend_mud", () -> new MudBlock(BlockBehaviour.Properties.copy(Blocks.DIRT).color(MaterialColor.TERRACOTTA_CYAN).sound(SoundType.MUD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> PACKED_SLIMEND_MUD = register("packed_slimend_mud", () -> new Block(BlockBehaviour.Properties.copy(Blocks.PACKED_MUD).color(MaterialColor.TERRACOTTA_YELLOW).sound(SoundType.MUD_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SLIMEND_MUD_BRICK = register("slimend_mud_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).color(MaterialColor.TERRACOTTA_YELLOW).sound(SoundType.MUD_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);


	public static final RegistryObject<Block> END_GRASS = register("end_grass", () -> new EndGrassBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.SAND).sound(SoundType.GRASS).instabreak().noCollission()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ENDERLAWN = register("enderlawn", () -> new EndlawnBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).requiresCorrectToolForDrops().randomTicks().sound(SoundType.NYLIUM).strength(3.5F, 9.5F)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ENDERBITE_COCOON = register("enderbite_cocoon", () -> new EnderbiteCocoonBlock(BlockBehaviour.Properties.of(Material.EGG).sound(SoundType.WOOL).strength(0.45F, 0.75F).randomTicks()), CreativeModeTab.TAB_MATERIALS);
	public static final RegistryObject<Block> ENDERMITE_EGG = register("endermite_egg", () -> new EndermiteEggBlock(BlockBehaviour.Properties.of(Material.EGG).sound(SoundType.METAL).strength(0.25F, 0.5F).randomTicks()), CreativeModeTab.TAB_MATERIALS);

	private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
		RegistryObject<T> register = BLOCKS.register(name, block);
		ModItems.ITEMS.register(name, item.apply(register));
		return register;
	}

	private static <T extends Block> RegistryObject<T> noItemRegister(String name, Supplier<? extends T> block) {
		RegistryObject<T> register = BLOCKS.register(name, block);
		return register;
	}

	private static <B extends Block> RegistryObject<B> register(String name, Supplier<? extends Block> block, CreativeModeTab creativeModeTab) {
		return (RegistryObject<B>) baseRegister(name, block, (registryObject) -> registerBlockItem(registryObject, creativeModeTab));
	}

	private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block, CreativeModeTab creativeModeTab) {
		return () -> {
			return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties().tab(creativeModeTab));
		};
	}
}
