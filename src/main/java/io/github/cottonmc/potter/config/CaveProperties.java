package io.github.cottonmc.potter.config;

public class CaveProperties {
	public int branchFactor;
	public int maxCaveCount;
	public int maxCaveHeight;
	public double tunnelSystemAspectRatio;
	public float tunnelSystemSizeFactor;
	public int bigTunnelSystemRarity;
	public float bigTunnelSystemSizeFactor;

	public CaveProperties(int branchFactor, int maxCaveCount, int maxCaveHeight, double tunnelSystemAspectRatio, float tunnelSystemSizeFactor, int bigTunnelSystemRarity, float bigTunnelSystemSizeFactor) {
		this.branchFactor = branchFactor;
		this.maxCaveCount = maxCaveCount;
		this.maxCaveHeight = maxCaveHeight;
		this.tunnelSystemAspectRatio = tunnelSystemAspectRatio;
		this.tunnelSystemSizeFactor = tunnelSystemSizeFactor;
		this.bigTunnelSystemRarity = bigTunnelSystemRarity;
		this.bigTunnelSystemSizeFactor = bigTunnelSystemSizeFactor;
	}
}
