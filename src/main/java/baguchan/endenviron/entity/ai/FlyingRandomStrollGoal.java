package baguchan.endenviron.entity.ai;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class FlyingRandomStrollGoal extends RandomStrollGoal
{
	protected final float probability;

	public FlyingRandomStrollGoal(PathfinderMob creatureEntity, double speed) {
		this(creatureEntity, speed, 120, 0.001F);
	}

	public FlyingRandomStrollGoal(PathfinderMob creatureEntity, double speed, int interval) {
		this(creatureEntity, speed, interval, 0.001F);
	}

	public FlyingRandomStrollGoal(PathfinderMob creatureEntity, double speed, int interval, float probability) {
		super(creatureEntity, speed, interval);
		this.probability = probability;
	}




	@Nullable
	protected Vec3 getPosition() {
		if (this.mob.isInWaterOrBubble()) {
			Vec3 vec3d = DefaultRandomPos.getPos(this.mob, 15, this.mob.getMaxFallDistance());
			return vec3d == null ? super.getPosition() : vec3d;
		} else if (!this.mob.isOnGround()) {
			Vec3 vec3d = DefaultRandomPos.getPos(this.mob, 12, this.mob.getMaxFallDistance());
			return vec3d != null ? vec3d : super.getPosition();
		} else {
			return this.mob.getRandom().nextFloat() >= this.probability ? DefaultRandomPos.getPos(this.mob, 10, this.mob.getMaxFallDistance()) : super.getPosition();
		}
	}
}