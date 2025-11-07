package org.jetbrains.kotlinx.tictactoe

/**
 * An interface representing a player (human or AI).
 * This makes the game extensible to new player types.
 */
interface Player {
    val name: String
    val mark: Mark

    /**
     * returns An integer (0-8) representing the chosen cell.
     */
    fun getMove(board: Board): Int
}