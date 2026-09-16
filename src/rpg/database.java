package rpg;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class database {

	public static player loadPlayer(String fileName) throws IOException {
		String json = readFile(fileName);
		ArrayList<String> inventory = stringArray(json, "inventory");

		player loadedPlayer = new player(
				stringValue(json, "name"),
				intValue(json, "maxHealth"),
				intValue(json, "level"),
				intValue(json, "exp"),
				intValue(json, "attack"),
				inventory);
		loadedPlayer.health = Math.max(0, Math.min(
				loadedPlayer.maxhealth, intValue(json, "health")));
		return loadedPlayer;
	}

	public static ArrayList<enemy> loadEnemyPresets(String fileName) throws IOException {
		String json = readFile(fileName);
		ArrayList<enemy> presets = new ArrayList<>();

		for (String enemyJson : objectsInArray(json)) {
			presets.add(new enemy(
					stringValue(enemyJson, "name"),
					intValue(enemyJson, "health"),
					intValue(enemyJson, "attack"),
					new ArrayList<>(),
					stringValue(enemyJson, "race"),
					intValue(enemyJson, "expReward"),
					doubleValue(enemyJson, "fleeChance"),
					booleanValue(enemyJson, "canHeal")));
		}
		return presets;
	}

	public static void savePlayer(String fileName, player playerToSave) throws IOException {
		StringBuilder json = new StringBuilder();
		json.append("{\n");
		json.append("  \"name\": \"").append(escape(playerToSave.name)).append("\",\n");
		json.append("  \"maxHealth\": ").append(playerToSave.maxhealth).append(",\n");
		json.append("  \"health\": ").append(playerToSave.health).append(",\n");
		json.append("  \"level\": ").append(playerToSave.level).append(",\n");
		json.append("  \"exp\": ").append(playerToSave.exp).append(",\n");
		json.append("  \"attack\": ").append(playerToSave.attack).append(",\n");
		json.append("    \"inventory\": [");
		for (int i = 0; i < playerToSave.inventory.size(); i++) {
			if (i > 0) {
				json.append(", ");
			}
			json.append("\"").append(escape(playerToSave.inventory.get(i))).append("\"");
		}
		json.append("\n  ]\n}\n");
		Files.writeString(Path.of(fileName), json.toString(), StandardCharsets.UTF_8);
	}

	private static String readFile(String fileName) throws IOException {
		return Files.readString(Path.of(fileName), StandardCharsets.UTF_8);
	}

	private static String stringValue(String json, String key) {
		return match(json, "\\\"" + key + "\\\"\\s*:\\s*\\\"([^\\\"]*)\\\"");
	}

	private static int intValue(String json, String key) {
		return Integer.parseInt(match(json, "\\\"" + key + "\\\"\\s*:\\s*(-?\\d+)"));
	}

	private static double doubleValue(String json, String key) {
		return Double.parseDouble(match(json, "\\\"" + key + "\\\"\\s*:\\s*(-?\\d+(?:\\.\\d+)?)"));
	}

	private static boolean booleanValue(String json, String key) {
		return Boolean.parseBoolean(match(json, "\\\"" + key + "\\\"\\s*:\\s*(true|false)"));
	}

	private static ArrayList<String> stringArray(String json, String key) {
		String values = arrayForKey(json, key);
		ArrayList<String> result = new ArrayList<>();
		Matcher matcher = Pattern.compile("\\\"([^\\\"]*)\\\"").matcher(values);
		while (matcher.find()) {
			result.add(matcher.group(1));
		}
		return result;
	}

	private static String objectForKey(String json, String key) {
		return balancedValue(json, key, '{', '}');
	}

	private static String arrayForKey(String json, String key) {
		return balancedValue(json, key, '[', ']');
	}

	private static String balancedValue(String json, String key, char opening, char closing) {
		Matcher matcher = Pattern.compile("\\\"" + key + "\\\"\\s*:").matcher(json);
		if (!matcher.find()) {
			throw new IllegalArgumentException("Missing JSON key: " + key);
		}
		int start = json.indexOf(opening, matcher.end());
		int depth = 0;
		for (int i = start; i < json.length(); i++) {
			if (json.charAt(i) == opening) {
				depth++;
			} else if (json.charAt(i) == closing && --depth == 0) {
				return json.substring(start, i + 1);
			}
		}
		throw new IllegalArgumentException("Unclosed JSON value: " + key);
	}

	private static ArrayList<String> objectsInArray(String arrayJson) {
		ArrayList<String> objects = new ArrayList<>();
		int depth = 0;
		int start = -1;
		for (int i = 0; i < arrayJson.length(); i++) {
			if (arrayJson.charAt(i) == '{') {
				if (depth++ == 0) {
					start = i;
				}
			} else if (arrayJson.charAt(i) == '}' && --depth == 0) {
				objects.add(arrayJson.substring(start, i + 1));
			}
		}
		return objects;
	}

	private static String match(String json, String regex) {
		Matcher matcher = Pattern.compile(regex).matcher(json);
		if (!matcher.find()) {
			throw new IllegalArgumentException("Missing JSON value");
		}
		return matcher.group(1);
	}

	private static String escape(String value) {
		return value.replace("\\", "\\\\").replace("\"", "\\\"");
	}
}
