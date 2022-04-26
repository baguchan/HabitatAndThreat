package baguchan.habitat_and_threat.data;

import baguchan.habitat_and_threat.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class CraftingGenerator extends CraftingDataHelper {
	public CraftingGenerator(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		smithing(consumer, Items.SHIELD, ModItems.RAW_WILDFIRE_SHIELD.get(), ModItems.WILDFIRE_SHIELD.get());
	}
}