package baguchan.habitat_and_threat.registry;

import baguchan.habitat_and_threat.HabitatAndThreat;
import baguchan.habitat_and_threat.fluid.SlimendFluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, HabitatAndThreat.MODID);

	public static final RegistryObject<FlowingFluid> SLIMEND = FLUIDS.register("slimend", () -> new SlimendFluid.Source());
	public static final RegistryObject<FlowingFluid> SLIMEND_FLOW = FLUIDS.register("slimend_flow", () -> new SlimendFluid.Flowing());

}