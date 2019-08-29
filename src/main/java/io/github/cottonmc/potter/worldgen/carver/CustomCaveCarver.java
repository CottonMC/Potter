package io.github.cottonmc.potter.worldgen.carver;

import com.mojang.datafixers.Dynamic;
import io.github.cottonmc.potter.Potter;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.CaveCarver;

import java.util.Random;
import java.util.function.Function;

public class CustomCaveCarver extends CaveCarver {
	public CustomCaveCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> config, int heightLimit) {
		super(config, heightLimit);
	}

	@Override
	public int getBranchFactor() {
		return Potter.config.overworldCaves.branchFactor;
	}

	@Override
	protected int getMaxCaveCount() {
		return Potter.config.overworldCaves.maxCaveCount;
	}

	@Override
	protected double getTunnelSystemHeightWidthRatio() {
		return Potter.config.overworldCaves.tunnelSystemAspectRatio;
	}

	@Override
	protected float getTunnelSystemWidth(Random rand) {
		float size = rand.nextFloat() * Potter.config.overworldCaves.tunnelSystemSizeFactor + rand.nextFloat();
		if (rand.nextInt(Potter.config.overworldCaves.bigTunnelSystemRarity) == 0) {
			size *= rand.nextFloat() * rand.nextFloat() * Potter.config.overworldCaves.bigTunnelSystemSizeFactor + 1.0F;
		}

		return size;
	}

	protected int getCaveY(Random rand) {
		int firstCap = Potter.config.overworldCaves.maxCaveHeight - 8;
		return rand.nextInt(rand.nextInt(firstCap) + 8);
	}

	@Override
	protected boolean isRegionUncarvable(Chunk chunk, int int_1, int int_2, int int_3, int int_4, int int_5, int int_6, int int_7, int int_8) {
		if (!Potter.config.genAirCaves) return false;
		return super.isRegionUncarvable(chunk, int_1, int_2, int_3, int_4, int_5, int_6, int_7, int_8);
	}
}
