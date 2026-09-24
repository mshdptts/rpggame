package rpg;

import java.nio.file.Files;
import java.nio.file.Path;

public class main {
	public static void main(String[] args) {
		try {
			String playerFile = selectPlayerFile(args);
			game adventure = game.load(playerFile, "data/enemies.json");
			if (!adventure.getPlayer().isAlive()) {
				adventure.getPlayer().health = adventure.getPlayer().maxhealth;
				System.out.println("Your previous run ended in defeat. Restoring your health to continue.");
			}
			adventure.start();
			database.savePlayer("data/player-save.json", adventure.getPlayer());
			System.out.println("Game saved.");
		} catch (Exception exception) {
			System.out.println("Could not start game: " + exception.getMessage());
		}
	}

	private static String selectPlayerFile(String[] args) {
		if (args.length > 0 && "new".equalsIgnoreCase(args[0])) {
			System.out.println("Starting a new game from the baseline player stats.");
			return "data/player.json";
		}

		if (Files.exists(Path.of("data/player-save.json"))) {
			System.out.println("Loading saved player.");
			return "data/player-save.json";
		}

		System.out.println("No save found. Starting from the baseline player stats.");
		return "data/player.json";
	}
}
