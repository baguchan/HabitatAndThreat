package baguchan.habitat_and_threat.registry;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.fluidtype.SlimendFluidType;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidTypes {
	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, HabitatAndThreat.MODID);

	public static final RegistryObject<FluidType> SLIMEND = FLUID_TYPES.register("slimend", () -> new SlimendFluidType(FluidType.Properties.create().canHydrate(false).canExtinguish(true).motionScale(0.005F).density(850).viscosity(1200).fallDistanceModifier(0.1F).supportsBoating(true)));
}