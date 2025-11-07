Implementation of a library that  is able to drive a game of Tic-Tac-Toe, but leaves it up to users of the library to create the UI.

The app module uses the library to create the UI and allows for playing the game in command line.
Available modes for each of two players:
* Human (user input)
* easy AI (chooses one of possible moves at random)
* hard AI - uses Minimax algorithm to play the best moves (impossible to win when against it!)

Play with a friend, against AI or simulate games between bots.

To ensure clean user experience without Gradle progress messages obscuring the view run the app with:

```
.\gradlew.bat :app:run --quiet --console=plain
```

Other helpful commands:
```
.\gradlew.bat :app:run --quiet --console=plain --no-configuration-cache --rerun-tasks --refresh-dependencies
.\gradlew.bat clean test --no-configuration-cache --rerun-tasks --refresh-dependencies
```