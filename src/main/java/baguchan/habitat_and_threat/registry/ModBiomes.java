package baguchan.habitat_and_threat.registry;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.world.gen.ModPlacements;
import com.teamabnormals.blueprint.core.util.BiomeUtil;
import com.teamabnormals.blueprint.core.util.registry.BiomeSubRegistryHelper;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HabitatAndThreat.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes {
	private static final BiomeSubRegistryHelper HELPER = HabitatAndThreat.REGISTRY_HELPER.getBiomeSubHelper();

	public static final BiomeSubRegistryHelper.KeyedBiome END_WILD = HELPER.createBiome("end_wild", ModBiomes::createEndWild);

	@SuppressWarnings("unchecked")
	public static void setupBiomeInfo() {
		BiomeDictionary.addTypes(END_WILD.getKey(), BiomeDictionary.Type.END, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.PLAINS);
		BiomeUtil.markEndBiomeCustomMusic(END_WILD.getKey());
	}

	private static Biome createEndWild() {
		MobSpawnSettings mobspawnsettings = (new MobSpawnSettings.Builder()).addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.ENDERBITE.get(), 10, 3, 4)).build();
		BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder());
		biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacements.PATCH_END_GRASS);

		return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.THEEND).temperature(0.5F).downfall(0.5F).specialEffects((new BiomeSpecialEffects.Builder()).skyColor(0)
				.waterColor(4159204)
				.waterFogColor(329011)
				.fogColor(10518688).skyColor(0).build()).mobSpawnSettings(mobspawnsettings).generationSettings(biomegenerationsettings$builder.build()).build();
	}
}
