package io.github.cottonmc.potter.worldgen.carver;

import com.mojang.datafixers.Dynamic;
import io.github.cottonmc.potter.Potter;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.UnderwaterCaveCarver;

import java.util.Random;
import java.util.function.Function;

public class CustomUnderwaterCaveCarver extends UnderwaterCaveCarver {
	public CustomUnderwaterCaveCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> config) {
		super(config);
	}

	@Override
	public int getBranchFactor() {
		return Potter.config.underwaterCaves.branchFactor;
	}

	@Override
	protected int getMaxCaveCount() {
		return Potter.config.underwaterCaves.maxCaveCount;
	}

	@Override
	protected double getTunnelSystemHeightWidthRatio() {
		return Potter.config.underwaterCaves.tunnelSystemAspectRatio;
	}

	@Override
	protected float getTunnelSystemWidth(Random rand) {
		float size = rand.nextFloat() * Potter.config.underwaterCaves.tunnelSystemSizeFactor + rand.nextFloat();
		if (rand.nextInt(Potter.config.underwaterCaves.bigTunnelSystemRarity) == 0) {
			size *= rand.nextFloat() * rand.nextFloat() * Potter.config.underwaterCaves.bigTunnelSystemSizeFactor + 1.0F;
		}

		return size;
	}

	protected int getCaveY(Random rand) {
		int firstCap = Potter.config.underwaterCaves.maxCaveHeight - 8;
		return rand.nextInt(rand.nextInt(firstCap) + 8);
	}

	@Override
	protected boolean isRegionUncarvable(Chunk chunk, int int_1, int int_2, int int_3, int int_4, int int_5, int int_6, int int_7, int int_8) {
		if (!Potter.config.genUnderwaterCaves) return false;
		return super.isRegionUncarvable(chunk, int_1, int_2, int_3, int_4, int_5, int_6, int_7, int_8);
	}
}
