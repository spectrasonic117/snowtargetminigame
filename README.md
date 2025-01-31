# Snow Target Minigame

## Overview

The Snow Target Minigame is a Minecraft plugin that allows players to engage in a fun and interactive minigame where they can freeze and unfreeze players using snowballs. This plugin enhances gameplay by adding unique mechanics and commands.

## Features

-   **Freeze Command**: Players can freeze others using snowballs.
-   **Unfreeze Command**: Players can unfreeze themselves or others.
-   **Cooldown Mechanism**: Players have a cooldown period after being unfrozen.
-   **Event Listeners**: Listens for player movements and projectile hits to manage freezing mechanics.

## Commands

-   `/defreeze <player>`: Unfreezes the specified player.
-   `/defreeze reload`: Reloads the plugin configuration.

## Configuration

The plugin comes with a default configuration file that can be modified to adjust various settings, such as:

-   `detection-distance`: The distance from the player to detect if they can be frozen.
-   `snowball-amount`: The number of snowballs given to a frozen player.
-   `cooldown-time`: The time in seconds before a player can be frozen again after being unfrozen.

## Installation

1. Download the plugin JAR file.
2. Place the JAR file in the `plugins` folder of your Minecraft server.
3. Restart the server to enable the plugin.
4. Configure the plugin settings in `config.yml` as needed.

## Dependencies

-   This plugin requires a server running Paper or Spigot.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements or bug fixes.
