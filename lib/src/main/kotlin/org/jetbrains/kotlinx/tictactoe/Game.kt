package org.jetbrains.kotlinx.tictactoe

/**
 * The main game engine.
 */
class Game(
    val playerX: Player,
    val playerO: Player
) {
    var board: Board = Board.EMPTY
        private set

    var gameState: GameState = GameState.Ongoing(Mark.X)
        private set

    /**
     * Plays a single turn in the game.
     */
    fun playTurn(): GameState {
        if (gameState !is GameState.Ongoing) {
            return gameState // Game already over
        }

        val currentPlayerMark = (gameState as GameState.Ongoing).currentPlayer
        val currentPlayer = if (currentPlayerMark == Mark.X) playerX else playerO

        val move = currentPlayer.getMove(board)

        board = board.makeMove(move, currentPlayerMark) //apply the move

        gameState = GameLogic.checkGameState(board, currentPlayerMark)

        return gameState
    }
}