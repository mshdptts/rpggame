# RPG Game

A simple terminal based RPG game made with Java

## Features

- Player system
- Enemy system
- Item system
- Turn based combat
- Player leveling (no additional features added yet)
- Experience and gold (to be added)
- Inventory
- JSON game data
- Save and load system
- Multiple enemies
- Multiple items (to be added)
- Main game loop
- Manual JSON save and load

## Technologies

- Java
- JSON
- Object Oriented Programming

## Project Structure

```text
RPGGame/
│
├── src/
│   ├── Main.java
│   ├── Game.java
│   ├── Player.java
│   ├── Enemy.java
│   ├── Item.java
│   ├── Combat.java
│   └── Database.java
│
├── data/
│   ├── enemies.json
│   └── items.json
│
├── saves/
│   └── save.json
│
└── README.md
```

## File Responsibilities

### Main.java

Starts the program

Creates the game object

Starts the game

### Game.java

Controls the main game loop

Handles menus

Handles exploration

Connects the different systems together

### Player.java

Represents the player

Stores player information

```text
Name
HP
Max HP
Attack
Defense
Level
Experience
Gold
Inventory
Location
```

### Enemy.java

Represents enemies

Stores enemy information

```text
Name
HP
Max HP
Attack
Defense
Experience reward
Gold reward
```

### Item.java

Represents items

Stores item information

```text
Name
Type
Value
```

### Combat.java

Handles battles between the player and enemies

Handles

```text
Attacking
Taking damage
Using items
Checking if someone is defeated
Giving experience
Giving gold
```

### Database.java

Handles JSON data

Responsible for

```text
Saving the game
Loading the game
Loading enemy data
Loading item data

### JSON data files

`data/player.json` stores the player data. `data/enemies.json` stores the enemy
presets. The combat test loads both files, then writes the updated player to
`data/player-save.json` when the battle ends.

The JSON is parsed manually, so no external JSON library is required.
```

## JSON Files

### enemies.json

Contains the predefined enemy data

Example

```json
{
  "slime": {
    "name": "Slime",
    "hp": 30,
    "attack": 5,
    "defense": 2
  }
}
```

### items.json

Contains the predefined item data

Example

```json
{
  "potion": {
    "name": "Health Potion",
    "type": "healing",
    "value": 30
  }
}
```

### save.json

Contains the player's current progress

Example

```json
{
  "name": "Hero",
  "level": 1,
  "hp": 100,
  "maxHp": 100,
  "attack": 10,
  "defense": 5,
  "gold": 0,
  "inventory": [],
  "location": "village"
}
```

## Game Flow

```text
Start Game
    ↓
Main Menu
    ↓
New Game / Load Game
    ↓
Create Player
    ↓
Explore
    ↓
Encounter Enemy
    ↓
Combat
    ↓
Gain EXP and Gold
    ↓
Continue Exploring
    ↓
Save Game
    ↓
Continue / Exit
```

## Development Order

The project is developed in this order

```text
1. Player.java
2. Enemy.java
3. Item.java
4. Combat.java
5. Database.java
6. Game.java
7. Main.java
8. JSON files
```

## Requirements

- Java JDK 17 or newer
- A Java IDE or code editor
- JSON library such as Gson

## How to Run

Compile the Java source files

```bash
javac src/*.java
```

Run the program

```bash
java -cp src Main
```

## Future Features

- More enemies
- More items
- Weapons and armor
- Skills
- NPCs
- Quests
- Shops
- Multiple locations
- Boss battles
- Dialogue system
- Multiple save slots
- Story system
- More advanced combat
- add responsive gui

## Goal

The goal of this project is to create a simple terminal based RPG while practicing Java Object Oriented Programming file organization JSON data storage and game development concepts
