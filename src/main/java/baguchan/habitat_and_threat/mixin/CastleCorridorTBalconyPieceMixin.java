package baguchan.habitat_and_threat.mixin;

import baguchan.habitat_and_threat.entity.WildFire;
import baguchan.habitat_and_threat.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.NetherBridgePieces;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(NetherBridgePieces.CastleCorridorTBalconyPiece.class)
public abstract class CastleCorridorTBalconyPieceMixin extends NetherBridgePieces.NetherBridgePiece {

	protected CastleCorridorTBalconyPieceMixin(StructurePieceType p_209887_, int p_209888_, BoundingBox p_209889_) {
		super(p_209887_, p_209888_, p_209889_);
	}

	@Inject(method = "postProcess", at = @At("TAIL"))
	public void postProcess(WorldGenLevel p_192136_, StructureFeatureManager p_192137_, ChunkGenerator p_192138_, Random p_192139_, BoundingBox p_192140_, ChunkPos p_192141_, BlockPos p_192142_, CallbackInfo callbackInfo) {
		BlockPos blockpos = this.getWorldPos(4, 3, 5);

		if (p_192140_.isInside(blockpos)) {
			WildFire wildFire = ModEntities.WILDFIRE.get().create(p_192136_.getLevel());
			wildFire.setPersistenceRequired();
			wildFire.setPos(blockpos.getX(), blockpos.getY(), blockpos.getZ());
			wildFire.finalizeSpawn(p_192136_, p_192136_.getCurrentDifficultyAt(wildFire.blockPosition()), MobSpawnType.STRUCTURE, (SpawnGroupData) null, (CompoundTag) null);
			p_192136_.addFreshEntity(wildFire);
		}
	}
}
