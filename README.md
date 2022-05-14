# Sci-fi Ship Escape
### Description/Work Division

Our program is a top-down shooter. It begins by initializing a 2D array that consists of tiles. Each type of tile will have its own image that will be printed to JFrame. The player’s unit will then be initialized with health and strength, and x and y coordinates. The player will have its own image which will be printed in JFrame over the tiles. The player will be able to move, and every frame the player will be redrawn on the correct coordinates, which will give the illusion of movement. The player can move into other rooms in which enemies will be initialized and drawn. The player will not be able to progress until all the enemies in the room have been eliminated. After clearing all the rooms, the player will encounter a boss, and upon defeating the boss they will escape the ship and the game will be over.

We plan for Shane to create the graphics and UI because I have the most experience with JFrame, we plan for Evan to create the codespace for the game. This means he will create a board that consists of tiles. (Shane and Evan will work on the game loop and update the screen in tandem.) We plan for Luke to be responsible for the creation of the entities. This means he will create the Entity class, and any classes that might inherit from it. Finally, Carter will be responsible for creating the tiles, and implementing any classes that inherit from tiles as we plan to make different tiles do different things.

### Screen Example
![image](https://user-images.githubusercontent.com/56329378/168218420-7108c3be-c736-4ff9-a500-10d7e51f536f.png)
