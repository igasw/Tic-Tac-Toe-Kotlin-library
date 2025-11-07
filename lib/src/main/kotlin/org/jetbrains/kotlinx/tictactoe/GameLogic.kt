package org.jetbrains.kotlinx.tictactoe


sealed class GameState {
    /** 'currentPlayer' is to move. */
    data class Ongoing(val currentPlayer: Mark) : GameState()

    data class Win(val winner: Mark) : GameState()

    object Draw : GameState()
}

object GameLogic {

    // winning combinations
    private val WINNING_LINES = listOf(
        // Rows
        listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
        // Columns
        listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
        // Diagonals
        listOf(0, 4, 8), listOf(2, 4, 6)
    )

    fun checkGameState(board: Board, lastPlayer: Mark): GameState {
        // Check if the last player to move has won
        for (line in WINNING_LINES) {
            if (line.all { board.getMarkAt(it) == lastPlayer }) {
                return GameState.Win(lastPlayer)
            }
        }

        // If no win, check for a draw
        if (board.isFull) {
            return GameState.Draw
        }

        // else,- the game continues
        return GameState.Ongoing(lastPlayer.opponent)
    }
}