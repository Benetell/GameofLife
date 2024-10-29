# Game of Life

A Java implementation of Conway's Game of Life with a graphical interface. This project provides a simple interactive version of the Game of Life, where users can visualize the evolution of cells based on predefined rules.

## Overview

Conway's Game of Life is a cellular automaton devised by mathematician John Conway. It consists of a grid of cells that evolve through generations based on a set of simple rules:
1. Any live cell with two or three live neighbors survives.
2. Any dead cell with exactly three live neighbors becomes a live cell.
3. All other live cells die in the next generation. Similarly, all other dead cells stay dead.

The project uses a graphical user interface (GUI) to display the evolving grid and allows users to control various aspects of the simulation.

## Files

### Core Files
- **Main.java**: The main entry point of the application. Initializes the game window and sets up the panels for the GUI.
- **GameFrame.java**: The main frame that holds all panels and manages the layout.
- **GamePanel.java**: The panel responsible for displaying the grid and handling the evolution of cells over generations.
- **cell.java**: Represents a single cell in the grid, with properties like its state (alive or dead) and methods to update its state.

### UI Components
- **ControlPanel.java**: Contains controls to start, pause, reset, and change the speed of the simulation. It enables users to interact with the game.
- **InfoPanel.java**: Displays additional information, such as generation count and other game stats.
- **MainPanel.java**: The main container that organizes the GamePanel, ControlPanel, and InfoPanel into a cohesive layout for the game display.

### Testing
- **Tests.java**: Contains test cases to ensure the correct functionality of the core components and the game logic.

## Features

- **Interactive Grid**: Users can interact with the grid by clicking on cells to toggle them between alive and dead states before starting the simulation.
- **Control Options**: Start, pause, reset, and control the speed of the simulation with ease.
- **Information Display**: See the current generation number and other game statistics.

## Requirements

- **Java 8 or later**

## Getting Started

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/GameOfLife.git
2. Open the project in your preferred Java IDE.
3. Compile and run the Main.java file to start the game.
