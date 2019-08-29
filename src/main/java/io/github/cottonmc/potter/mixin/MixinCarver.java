package io.github.cottonmc.potter.mixin;

import io.github.cottonmc.potter.worldgen.carver.CustomCaveCarver;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Carver.class)
public class MixinCarver {
	@ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/carver/Carver;register(Ljava/lang/String;Lnet/minecraft/world/gen/carver/Carver;)Lnet/minecraft/world/gen/carver/Carver;"))
	private static Carver<ProbabilityConfig> replaceCaveCarver(Carver<ProbabilityConfig> orignal) {
		return new CustomCaveCarver(ProbabilityConfig::deserialize, 256);
	}


}
