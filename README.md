game
====

First project for CompSci 308 Fall 2016

- names of all people who worked on the project
	 - Matthew Faw 
 - date you started, date you finished, and an estimate of the number of hours worked on the project 
	 - I started planning my project on September 1, and made my first commit on the morning of September 3. I made my final commit to the project on September 12.  I probably spent about 3-5hrs on planning/research, and 35 hrs on implementing the game.
 - each person's role in developing the project 
	 - I developed the game on my own.
 - any books, papers, online, or human resources that you used in developing the project 
	 - The only resources I used were StackOverflow and Oracle docs/tutorials on random Java and JavaFX tutorials and feature explanations.
 - files used to start the project (the class(es) containing
   main) 
	 - The file used to start the project is called Main.java, and it lives in the src/default package directory.
 - files used to test the project 
	 - I did not use any files to test the project.  Instead, I slowly added features, and tested those features one by one by playing the game.
 - any data or resource files required by the project (including format of non-standard files)
	 - My project requires Image files (.jpg), all of which live in the images directory.  My project also requires music files (.mp3), so make sure you have your volume turned up to enjoy the soothing tunes of Kanye :P.  Whenever a song plays, I get a warning message saying that the library I am using to play the music is depreciated and will be removed in a future release. However, I could not seem to find the "correct" library to use for music playing.  I had no issues playing the music on my machine.
 - any information about using the program (i.e., command-line/applet
   arguments, key inputs, interesting example data files, or easter
   eggs) 
    - When the game starts, it opens to a splash screen, explaining how the game is played.  To start the game, a player either selects "Easy Game" or "Hard Game" by clicking on one of the two buttons. The game opens, and you become Kanye, about to drop out of college.  The only way to progress in the scene is to walk away from college and jump off the ledge, symbolizing Kanye's dropping out of college. To make Kanye move left and right, use the left and right arrow keys, respectively.  Kanye can also jump by pressing the down arrow. After dropping out of college, Kanye progresses to the forest scene, where he meets his first enemy, the paparazzi, who are represented by cameras.  The objective of the level is to jump on the photographers to destroy them.  If the photographers walk over Kanye, then he loses health points.  Kanye must collect at least ceil(3/4 * number_of_gold_coins) in order to progress to the next level. Once he collects the coins and takes the tunnel on the left, he is transported to the final level, where he faces his ultimate enemy--Taylor Swift.  He no longer has the luxury of jumping up and down; he can move left, right, up, or down in the screen only.  There are lots of Taylors, and not lots of screen real estate, so Kanye has a new power--"spitting fire." Using the keys h, j, k, and l to shoot fireballs left, down, up, and right, respectively, he can destroy all of the Taylor Swift enemies.  He must find his way to Kim in this scene.  Once he reaches her, he is transported to an empty scene where Kanye falls off the screen, symbolizing his "falling" for Kim Kardashian.  The music in the background plays "Champion", as Kanye has won the game.  If any one of the enemies he encounters along the way brings his health to 0%, then he loses the game.
    - I sprinkled a few easter eggs throughout the game.  By pressing the letter 't', Kanye can automatically transition to the next scene in the game. By pressing the letter 'd', the enemies are automatically disabled, and can no longer harm Kanye.  Press 'r' to resume the enemies' normal behavior.  
 - any known bugs, crashes, or problems with the project's functionality 
	 - In the first two scenes (the jumping scenes), If Kanye climbs onto an object that's not an active tunnel, then he can actually fall out of game boundaries, and the game is not declared to be over.  Additionally, if you press t after he falls off the screen, Kanye still proceeds to the next scene.
	 - In the final scene where "Champion" plays, you can press 't' and transition back to the beginning of the game.  I designed it this way, since 't' is technically an easter egg.
	 - When the character dies, there is no option to reset the game. Additionally, pressing 't' will still transition Kanye to the next scene.  Although, whenever he touches any enemy, he immediately dies, since he is out of health points.
 - any extra features included in the project 
	 - All features were discuessed in the previous two sections
 - your impressions of the assignment to help improve it in the future
	 - I think introducing some basic design patterns (MVC) formally to the class could help everyone design a much better game that is more easily modifiable.  Not introducing this concept somewhat seems like implicitly punishing the people who don't know about it, as they would likely approach the game in a very haphazard way that would lead to many headaches in adding features to the game.