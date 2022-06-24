package baguchan.habitat_and_threat.data;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.registry.ModBlocks;
import baguchan.habitat_and_threat.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemModelGenerator extends ItemModelProvider {
	public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, HabitatAndThreat.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		this.toBlock(ModBlocks.ENDERLAWN.get());
		this.itemBlockFlat(ModBlocks.END_GRASS.get());
		this.toBlock(ModBlocks.ENDERBITE_COCOON.get());
		this.toBlock(ModBlocks.ENDERMITE_EGG.get());
		this.egg(ModItems.WILDFIRE_SPAWN_EGG.get());
		this.egg(ModItems.ENDERBITE_SPAWN_EGG.get());

		this.singleTex(ModItems.SLIMEND_BUCKET.get());
		this.toBlock(ModBlocks.SLIMEND_MUD.get());
		this.toBlock(ModBlocks.PACKED_SLIMEND_MUD.get());
		this.toBlock(ModBlocks.SLIMEND_MUD_BRICK.get());
	}

	private ItemModelBuilder generated(String name, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, "item/generated");
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		return builder;
	}


	private ItemModelBuilder tool(String name, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, "item/handheld");
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		return builder;
	}

	private ItemModelBuilder singleTex(Item item) {
		return generated(ForgeRegistries.ITEMS.getKey(item).getPath(), prefix("item/" + ForgeRegistries.ITEMS.getKey(item).getPath()));
	}

	private ItemModelBuilder bowItem(String name, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, "item/bow");
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		return builder;
	}

	private void woodenButton(Block button, String variant) {
		getBuilder(ForgeRegistries.BLOCKS.getKey(button).getPath())
				.parent(getExistingFile(mcLoc("block/button_inventory")))
				.texture("texture", "block/wood/planks_" + variant + "_0");
	}

	private void woodenFence(Block fence, Block block) {
		getBuilder(ForgeRegistries.BLOCKS.getKey(fence).getPath())
				.parent(getExistingFile(mcLoc("block/fence_inventory")))
				.texture("texture", "block/" + ForgeRegistries.BLOCKS.getKey(block).getPath());
	}

	private void woodenFence(Block fence, String texture) {
		getBuilder(ForgeRegistries.BLOCKS.getKey(fence).getPath())
				.parent(getExistingFile(mcLoc("block/fence_inventory")))
				.texture("texture", "block/" + texture);
	}

	private void toBlock(Block b) {
		toBlockModel(b, ForgeRegistries.BLOCKS.getKey(b).getPath());
	}

	private void toBlockModel(Block b, String model) {
		toBlockModel(b, prefix("block/" + model));
	}

	private ResourceLocation prefix(String s) {
		return new ResourceLocation(HabitatAndThreat.MODID, s);
	}

	private void toBlockModel(Block b, ResourceLocation model) {
		withExistingParent(ForgeRegistries.BLOCKS.getKey(b).getPath(), model);
	}

	public ItemModelBuilder itemBlockFlat(Block block) {
		return itemBlockFlat(block, blockName(block));
	}

	public ItemModelBuilder itemBlockFlat(Block block, String name) {
		return withExistingParent(blockName(block), mcLoc("item/generated"))
				.texture("layer0", modLoc("block/" + name));
	}

	public ItemModelBuilder egg(Item item) {
		return withExistingParent(ForgeRegistries.ITEMS.getKey(item).getPath(), mcLoc("item/template_spawn_egg"));
	}

	public String blockName(Block block) {
		return ForgeRegistries.BLOCKS.getKey(block).getPath();
	}

	@Override
	public String getName() {
		return "HabitatAndThreat item and itemblock models";
	}
}