# ScalaPong
The iconic game of Pong made entirely in Scala.

## Use of AI
This project was built with the help of Claude.ai. The LLM helped with the planning, the organization, the setup of the engine and gradle (modified from the template of the setup from the Tetris assignment), and served as a mentor to give the general idea as for what the goal of each file is. Also helped in designing the skeleton of the main files such as GameLogic.scala and Collision.scala, and designing and explaining the Input handler.

The rest of the code was written by me.

## FEATURES
- 2-player mode and 1-player mode against a simple AI
- Start menu: choose the number of players and the ball speed (Slow/Normal/Fast)
- The ball leaves a paddle at an angle that depends on where it hit
- First to 5 points wins

## CONTROLS
- Left player: W / S
- Right player: Up / Down arrows
- Menu: Up/Down changes the number of players, Left/Right changes the speed,
  Enter starts the game
- Game over screen: Space returns to the menu
- In 1-player mode, W/S and the arrow keys both move the left paddle
