package org.jetbrains.kotlinx.tictactoe

/**
 * An "Easy" AI player that chooses a move randomly from available spots.
 */
class RandomPlayer(
    override val name: String,
    override val mark: Mark
) : Player {
    override fun getMove(board: Board): Int {
        return board.availableMoves.random()
    }
}