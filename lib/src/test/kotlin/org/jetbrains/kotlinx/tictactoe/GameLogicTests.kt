package org.jetbrains.kotlinx.tictactoe

import kotlin.test.*

class GameLogicTests {

    @Test
    fun `detects row win`() {
        val board = Board.EMPTY
            .makeMove(0, Mark.X)
            .makeMove(3, Mark.O)
            .makeMove(1, Mark.X)
            .makeMove(4, Mark.O)
            .makeMove(2, Mark.X) // X wins on top row

        val state = GameLogic.checkGameState(board, Mark.X)
        assertTrue(state is GameState.Win, "State should be Win")
        assertEquals(Mark.X, state.winner)
    }

    @Test
    fun `detects column win`() {
        val board = Board.EMPTY
            .makeMove(0, Mark.O)
            .makeMove(1, Mark.X)
            .makeMove(3, Mark.O)
            .makeMove(2, Mark.X)
            .makeMove(6, Mark.O) // O wins on left col

        val state = GameLogic.checkGameState(board, Mark.O)
        assertTrue(state is GameState.Win, "State should be Win")
        assertEquals(Mark.O, state.winner)
    }

    @Test
    fun `detects diagonal win`() {
        val board = Board.EMPTY
            .makeMove(0, Mark.X)
            .makeMove(1, Mark.O)
            .makeMove(4, Mark.X)
            .makeMove(2, Mark.O)
            .makeMove(8, Mark.X) // X wins on diag

        val state = GameLogic.checkGameState(board, Mark.X)
        assertTrue(state is GameState.Win, "State should be Win")
        assertEquals(Mark.X, state.winner)
    }

    @Test
    fun `detects draw`() {
        // A known draw board state
        val board = Board.EMPTY
            .makeMove(0, Mark.X).makeMove(1, Mark.O).makeMove(2, Mark.X)
            .makeMove(3, Mark.X).makeMove(4, Mark.X).makeMove(5, Mark.O)
            .makeMove(6, Mark.O).makeMove(7, Mark.X).makeMove(8, Mark.O)

        val state = GameLogic.checkGameState(board, Mark.O)
        assertTrue(state is GameState.Draw, "State should be Draw")
    }

    @Test
    fun `detects ongoing game`() {
        val board = Board.EMPTY
            .makeMove(0, Mark.X)
            .makeMove(4, Mark.O)

        val state = GameLogic.checkGameState(board, Mark.O)
        assertTrue(state is GameState.Ongoing, "State should be Ongoing")
        assertEquals(Mark.X, state.currentPlayer)
    }
}