package baguchan.habitat_and_threat.registry;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.world.feature.EndVegetationFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {

	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, HabitatAndThreat.MODID);

	public static final RegistryObject<Feature<BlockStateConfiguration>> END_VEGETATION = FEATURES.register("end_vegetation", () -> new EndVegetationFeature(BlockStateConfiguration.CODEC));

}