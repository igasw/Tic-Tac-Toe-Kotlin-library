package org.jetbrains.kotlinx.tictactoe

import kotlin.test.*

class BoardTests {
    @Test
    fun `empty board has 9 available moves`() {
        val board = Board.EMPTY
        assertEquals(9, board.availableMoves.size)
        assertFalse(board.isFull)
    }

    @Test
    fun `makeMove places a mark`() {
        val board = Board.EMPTY.makeMove(0, Mark.X)
        assertEquals(Mark.X, board.getMarkAt(0))
        assertEquals(8, board.availableMoves.size)
        assertFalse(board.availableMoves.contains(0))
    }

    @Test
    fun `makeMove is immutable`() {
        val board1 = Board.EMPTY
        val board2 = board1.makeMove(0, Mark.X)

        assertNull(board1.getMarkAt(0)) // Original is unchanged
        assertEquals(Mark.X, board2.getMarkAt(0)) // New one has the mark
    }

    @Test
    fun `isFull is correct`() {
        var board = Board.EMPTY
        // Fill the board
        (0..8).forEach {
            board = board.makeMove(it, if (it % 2 == 0) Mark.X else Mark.O)
        }
        assertTrue(board.isFull)
        assertEquals(0, board.availableMoves.size)
    }

    @Test
    fun `cannot move to occupied spot`() {
        val board = Board.EMPTY.makeMove(0, Mark.X)
        assertFailsWith<IllegalArgumentException> {
            // Trying to move to the same spot
            board.makeMove(0, Mark.O)
        }
    }
}