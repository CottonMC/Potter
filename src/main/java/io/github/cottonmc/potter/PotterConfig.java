package io.github.cottonmc.potter;

import blue.endless.jankson.Comment;
import io.github.cottonmc.potter.config.CaveProperties;

public class PotterConfig {
	public boolean genAirCaves = true;
	@Comment("Not Yet Implemented")
	public boolean genAirRavines = true;
	public boolean genNetherCaves = true;
	public boolean genUnderwaterCaves = true;
	@Comment("Not Yet Implemented")
	public boolean genWaterRavines = true;

	public CaveProperties overworldCaves = new CaveProperties(4, 15, 128, 1D, 2F, 10, 3F);

	public CaveProperties underwaterCaves = new CaveProperties(4, 15, 128, 1D, 2F, 10, 3F);

	@Comment("Do not have big tunnel systems.")
	public CaveProperties netherCaves = new CaveProperties(4, 10, 128, 5D, 2F, -1, 0F);


}
