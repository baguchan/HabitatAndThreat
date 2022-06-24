package baguchan.habitat_and_threat.data;

import baguchan.habitat_and_threat.HabitatAndThreat;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class CraftingDataHelper extends RecipeProvider {
	public CraftingDataHelper(DataGenerator generator) {
		super(generator);
	}

	protected final void foodCooking(Item material, Item result, float xp, Consumer<FinishedRecipe> consumer) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(material), result, xp, 200).unlockedBy("has_item", has(material)).save(consumer, HabitatAndThreat.prefix("smelting_" + ForgeRegistries.ITEMS.getKey(result).getPath()));
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(material), result, xp, 100).unlockedBy("has_item", has(material)).save(consumer, HabitatAndThreat.prefix("smoking_" + ForgeRegistries.ITEMS.getKey(result).getPath()));
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(material), result, xp, 600).unlockedBy("has_item", has(material)).save(consumer, HabitatAndThreat.prefix("campfire_cooking_" + ForgeRegistries.ITEMS.getKey(result).getPath()));
	}

	protected final void foodCooking(Item material, Item result, float xp, Consumer<FinishedRecipe> consumer, String recipeName) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(material), result, xp, 200).unlockedBy("has_item", has(material)).save(consumer, HabitatAndThreat.prefix("smelting_" + recipeName));
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(material), result, xp, 100).unlockedBy("has_item", has(material)).save(consumer, HabitatAndThreat.prefix("smoking_" + recipeName));
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(material), result, xp, 600).unlockedBy("has_item", has(material)).save(consumer, HabitatAndThreat.prefix("campfire_cooking_" + recipeName));
	}

	protected final void smeltOre(Item material, Item result, float xp, Consumer<FinishedRecipe> consumer) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(material), result, xp, 200).unlockedBy("has_item", has(material)).save(consumer, HabitatAndThreat.prefix("smelting_" + ForgeRegistries.ITEMS.getKey(result).getPath()));
		SimpleCookingRecipeBuilder.blasting(Ingredient.of(material), result, xp, 100).unlockedBy("has_item", has(material)).save(consumer, HabitatAndThreat.prefix("blasting_" + ForgeRegistries.ITEMS.getKey(result).getPath()));
	}

	protected static void smithing(Consumer<FinishedRecipe> p_125995_, Item needItem, Item material, Item result) {
		UpgradeRecipeBuilder.smithing(Ingredient.of(needItem), Ingredient.of(material), result).unlocks("has_item", has(material)).save(p_125995_, HabitatAndThreat.prefix(getItemName(result) + "_smithing"));
	}


	protected final ResourceLocation locEquip(String name) {
		return HabitatAndThreat.prefix("equipment/" + name);
	}
}