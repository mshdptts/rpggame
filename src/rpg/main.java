package rpg;

public class main {
	public static void main(String[] args) {
		try {
			game adventure = game.load("data/player.json", "data/enemies.json");
			adventure.start();
			database.savePlayer("data/player-save.json", adventure.getPlayer());
			System.out.println("Game saved.");
		} catch (Exception exception) {
			System.out.println("Could not start game: " + exception.getMessage());
		}
	}
}
