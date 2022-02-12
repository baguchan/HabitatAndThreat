package baguchan.endenviron.registry;

import baguchan.endenviron.EndEnviron;
import com.google.common.collect.Sets;
import com.teamabnormals.blueprint.common.world.biome.modification.BiomeModificationManager;
import com.teamabnormals.blueprint.common.world.biome.modification.modifiers.BiomeSpawnsModifier;
import com.teamabnormals.blueprint.core.util.BiomeUtil;
import com.teamabnormals.blueprint.core.util.registry.BiomeSubRegistryHelper;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.Mod;

import java.util.function.BiPredicate;

@Mod.EventBusSubscriber(modid = EndEnviron.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes {
	private static final BiomeSubRegistryHelper HELPER = EndEnviron.REGISTRY_HELPER.getBiomeSubHelper();

	public static final BiomeSubRegistryHelper.KeyedBiome END_WILD = HELPER.createBiome("end_wild", ModBiomes::createEndWild);

	@SuppressWarnings("unchecked")
	public static void setupBiomeInfo() {
		BiomeDictionary.addTypes(END_WILD.getKey(), BiomeDictionary.Type.END, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.PLAINS);
		BiomeUtil.addEndBiome(Climate.parameters(-0.25F, 0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), END_WILD.getKey());
	}


	private static Biome createEndWild() {
		MobSpawnSettings mobspawnsettings = (new MobSpawnSettings.Builder()).build();
		BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder());
		return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.THEEND).temperature(0.5F).downfall(0.5F).specialEffects((new BiomeSpecialEffects.Builder()).skyColor(0)
				.waterColor(4159204)
				.waterFogColor(329011)
				.fogColor(10518688).skyColor(0).build()).mobSpawnSettings(mobspawnsettings).generationSettings(biomegenerationsettings$builder.build()).build();
	}
}
