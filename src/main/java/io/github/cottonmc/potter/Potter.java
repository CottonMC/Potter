package io.github.cottonmc.potter;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.JsonPrimitive;
import io.github.cottonmc.potter.config.CaveProperties;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;

public class Potter implements ModInitializer {

	public static final Logger logger = LogManager.getLogger();
	public static PotterConfig config;

	public static Jankson newJankson() {
		return Jankson.builder()
				.registerTypeAdapter(CaveProperties.class, Potter::loadProperties)
				.registerSerializer(CaveProperties.class, (t, marshaller) -> saveProperties(t))
				.build();
	}

	@Override
	public void onInitialize() {
		config = loadConfig();
	}

	public static CaveProperties loadProperties(JsonObject json) {
		int branchFactor = json.get(Integer.class, "branch_factor");
		int maxCaveCount = json.get(Integer.class, "max_cave_count");
		int maxCaveHeight = json.get(Integer.class, "max_cave_height");
		double tunnelSystemAspectRatio = json.get(Double.class, "tunnel_system_aspect_ratio");
		float tunnelSystemSizeFactor = json.get(Float.class, "tunnel_system_size_factor");
		int bigTunnelSystemRarity = json.get(Integer.class, "big_tunnel_system_rarity");
		float bigTunnelSystemSizeFactor = json.get(Float.class, "big_tunnel_system_size_factor");

		return new CaveProperties(branchFactor, maxCaveCount, maxCaveHeight, tunnelSystemAspectRatio, tunnelSystemSizeFactor, bigTunnelSystemRarity, bigTunnelSystemSizeFactor);
	}

	public static JsonObject saveProperties(CaveProperties properties) {
		JsonObject ret = new JsonObject();
		ret.put("branch_factor", new JsonPrimitive(properties.branchFactor));
		ret.put("max_cave_count", new JsonPrimitive(properties.maxCaveCount));
		ret.put("max_cave_height", new JsonPrimitive(properties.maxCaveHeight));
		ret.put("tunnel_system_aspect_ratio", new JsonPrimitive(properties.tunnelSystemAspectRatio));
		ret.put("tunnel_system_size_factor", new JsonPrimitive(properties.tunnelSystemSizeFactor));
		ret.put("big_tunnel_system_rarity", new JsonPrimitive(properties.bigTunnelSystemRarity));
		ret.put("bug_tunnel_system_size_factor", new JsonPrimitive(properties.bigTunnelSystemSizeFactor));
		return ret;
	}

	public PotterConfig loadConfig() {
		try {
			Jankson jankson = newJankson();
			File file = FabricLoader.getInstance().getConfigDirectory().toPath().resolve("potter.json5").toFile();
			if (!file.exists()) saveConfig(new PotterConfig());
			JsonObject json = jankson.load(file);
			PotterConfig result =  jankson.fromJson(json, PotterConfig.class);
			JsonElement jsonElementNew = jankson.toJson(new PotterConfig());
			if(jsonElementNew instanceof JsonObject){
				JsonObject jsonNew = (JsonObject) jsonElementNew;
				if(json.getDelta(jsonNew).size()>= 0){
					saveConfig(result);
				}
			}
		} catch (Exception e) {
			logger.error("Error loading config: {}", e.getMessage());
		}
		return new PotterConfig();
	}

	public void saveConfig(PotterConfig config) {
		try {
			File file = FabricLoader.getInstance().getConfigDirectory().toPath().resolve("potter.json5").toFile();
			JsonElement json = newJankson().toJson(config);
			String result = json.toJson(true, true);
			if (!file.exists()) file.createNewFile();
			FileOutputStream out = new FileOutputStream(file,false);
			out.write(result.getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error("Error saving config: {}", e.getMessage());
		}
	}
}
