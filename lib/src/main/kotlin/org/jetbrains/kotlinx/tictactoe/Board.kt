package org.jetbrains.kotlinx.tictactoe

/**
 * Represents the state of the 3x3 game board.
 * This class is immutable; `makeMove` returns a new Board instance.
 * @property cells The list of marks, where null represents an empty cell.
 */
class Board(private val cells: List<Mark?> = List(9) { null }) {

    fun getMarkAt(index: Int): Mark? = cells[index]

    val availableMoves: List<Int>
        get() = cells.mapIndexedNotNull { index, mark -> if (mark == null) index else null }

    val isFull: Boolean
        get() = availableMoves.isEmpty()

    fun makeMove(index: Int, mark: Mark): Board {
        if (cells[index] != null) {
            throw IllegalArgumentException("Position $index is already taken")
        }
        val newCells = cells.toMutableList()
        newCells[index] = mark
        return Board(newCells)
    }

    companion object {
        val EMPTY = Board() //shared instance of the empty board
    }
}