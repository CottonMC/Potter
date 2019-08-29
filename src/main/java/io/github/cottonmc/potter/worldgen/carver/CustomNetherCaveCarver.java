package io.github.cottonmc.potter.worldgen.carver;

import com.mojang.datafixers.Dynamic;
import io.github.cottonmc.potter.Potter;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.NetherCaveCarver;

import java.util.Random;
import java.util.function.Function;

public class CustomNetherCaveCarver extends NetherCaveCarver {
	public CustomNetherCaveCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> config) {
		super(config);
	}

	@Override
	public int getBranchFactor() {
		return Potter.config.netherCaves.branchFactor;
	}

	@Override
	protected int getMaxCaveCount() {
		return Potter.config.netherCaves.maxCaveCount;
	}

	@Override
	protected double getTunnelSystemHeightWidthRatio() {
		return Potter.config.netherCaves.tunnelSystemAspectRatio;
	}

	@Override
	protected float getTunnelSystemWidth(Random rand) {
		return (rand.nextFloat() * Potter.config.netherCaves.tunnelSystemSizeFactor + rand.nextFloat()) * Potter.config.netherCaves.tunnelSystemSizeFactor;
	}

	protected int getCaveY(Random rand) {
		return rand.nextInt(Potter.config.netherCaves.maxCaveHeight);
	}

	@Override
	protected boolean isRegionUncarvable(Chunk chunk, int int_1, int int_2, int int_3, int int_4, int int_5, int int_6, int int_7, int int_8) {
		if (!Potter.config.genNetherCaves) return false;
		return super.isRegionUncarvable(chunk, int_1, int_2, int_3, int_4, int_5, int_6, int_7, int_8);
	}
}
