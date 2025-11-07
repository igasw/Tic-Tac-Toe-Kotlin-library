package org.jetbrains.kotlinx.tictactoe

import kotlin.test.*

class MinimaxPlayerTests {

    @Test
    fun `minimax player blocks immediate loss`() {
        // Board state:
        // O | O | _
        // _ | X | _
        // X | _ | _
        // AI (X) must play at index 2 to block O from winning.
        val board = Board.EMPTY
            .makeMove(0, Mark.O) // Opponent
            .makeMove(4, Mark.X) // AI
            .makeMove(1, Mark.O) // Opponent
            .makeMove(6, Mark.X) // AI

        val aiPlayer = MinimaxPlayer("AI (X)", Mark.X)
        val move = aiPlayer.getMove(board)

        assertEquals(2, move, "AI should have blocked at index 2")
    }

    @Test
    fun `minimax player wins when possible`() {
        // Board state:
        // X | X | _
        // O | O | _
        // X | _ | _
        // AI (X) can win by playing at index 2.
        val board = Board.EMPTY
            .makeMove(0, Mark.X) // AI
            .makeMove(3, Mark.O) // Opponent
            .makeMove(1, Mark.X) // AI
            .makeMove(4, Mark.O) // Opponent
            .makeMove(6, Mark.X) // AI

        val aiPlayer = MinimaxPlayer("AI (X)", Mark.X)
        val move = aiPlayer.getMove(board)

        assertEquals(2, move, "AI should have played at index 2 to win")
    }

    @Test
    fun `minimax vs minimax is always a draw`() {
        val playerX = MinimaxPlayer("AI X", Mark.X)
        val playerO = MinimaxPlayer("AI O", Mark.O)
        val game = Game(playerX, playerO)

        // Play out the full game
        while (game.gameState is GameState.Ongoing) {
            game.playTurn()
        }

        // The game *must* end in a draw
        assertTrue(game.gameState is GameState.Draw, "Minimax vs Minimax should always be a draw")
    }
}