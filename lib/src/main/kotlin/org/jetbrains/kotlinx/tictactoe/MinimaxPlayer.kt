package org.jetbrains.kotlinx.tictactoe

/**
 * A "Hard" AI player that uses the Minimax algorithm to find the best possible move.
 */
class MinimaxPlayer(
    override val name: String,
    override val mark: Mark
) : Player {

    private val opponentMark = mark.opponent

    override fun getMove(board: Board): Int {
        return board.availableMoves
            .maxBy { move ->
                val newBoard = board.makeMove(move, mark)
                minimax(newBoard, opponentMark, false) //call Minimax for opponent
            }
    }

    /**
     * @return The heuristic score (1 win, -1 loss, 0 draw).
     */
    private fun minimax(board: Board, currentMark: Mark, isMaximizing: Boolean): Int {
        // Check for terminal states (win/loss/draw)
        val gameState = GameLogic.checkGameState(board, currentMark.opponent)

        when (gameState) {
            is GameState.Win -> {
                return if (gameState.winner == this.mark) 1 else -1
            }
            is GameState.Draw -> return 0 // 0 for a draw
            is GameState.Ongoing -> { /* Continue to recursive step */ }
        }

        if (isMaximizing) {
            // find the move with the max score
            var bestScore = Int.MIN_VALUE
            for (move in board.availableMoves) {
                val newBoard = board.makeMove(move, currentMark)
                val score = minimax(newBoard, currentMark.opponent, false)
                bestScore = maxOf(bestScore, score)
            }
            return bestScore
        } else {
            // current player is the opponent, find the move with the min score (it means max for opponent)
            var bestScore = Int.MAX_VALUE
            for (move in board.availableMoves) {
                val newBoard = board.makeMove(move, currentMark)
                val score = minimax(newBoard, currentMark.opponent, true)
                bestScore = minOf(bestScore, score)
            }
            return bestScore
        }
    }
}