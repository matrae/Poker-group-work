# Poker Mini-Project Documentation
 
Group members: Andrea Rippstein, Céline Mathis, Mattia Raemy

# Introduction

Our aim was to implement a menu in which you first choose the number of players and then start the game by pressing a button. Then you go on to the actual game in the second window and play poker in a modern-looking GUI. The idea was that you could end a game at any point with the goBack/back to menu button and choose a new number of players and start the game again (a new game). We wanted to improve the GUI to make it look like the cards had a felt background and a nice back-side. Whenever you hover over a button you can see  the hover effect and when you press the button it looks “clicked” for that time that you clicked it. We implemented a coin that would display the score to give it a bit of a game vibe.

# View adjustments
- We added the scene1. The scene1 is the menu. The menu was created with the intent to select the number of players (button twoPlayers to fourPlayers) & then click on startGame
- Added buttons: twoPlayers, threePlayers, fourPlayers
- Added startGame button so if the button would be pressed you go to the pokergame (actionevent implemented in the controller)
- Added goBack button so one could go back to the menu (actionevent implemented in the controller)
- Added background images for the menu and the game
- Added spaces between the cards, added rounded corners for the cards, added background image to the cards
- Added a score label in the player view and added a coin image into the score label
- Added all buttons to the CSS to have the opportunity to design them individually
- Redesigned all buttons ( button: hover, button: pressed, button: focused)


# Model Adjustments
- Added createPlayer so it would first clear and then create the players after the number of players had been selected
- Added (i+1) to the createPlayer logic so the number of players would be counted starting at 1, not zero
- Class handType
We implemented the logic for the different hand types (e.g. straight, full house) in the class handType. For the method isStraight we made use of a lambda expression to sort the cards before checking if it’s a straight or not.
- JUnit tests
To test whether or not the handType class works correctly we implemented some more tests. We did not do this for all hand types (to save some time) but as the most complex method isStraightFlush works correctly we assume that the rest works also correctly.



# Controller Adjustments
- Added action events so you are able to go to the second scene with startGame button and switch back from scene2 to scene1 with the goBack button
- Added action events for the selection of the number of players, defined for each button; twoPlayers, threePlayers and fourPlayers → when you select twoPlayers the game sets the Players equal to 2 using the method setPlayers

 