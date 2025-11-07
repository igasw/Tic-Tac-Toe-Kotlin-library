package org.jetbrains.kotlinx.tictactoe

// 'lib' module
import org.jetbrains.kotlinx.tictactoe.Mark
import org.jetbrains.kotlinx.tictactoe.Player
import org.jetbrains.kotlinx.tictactoe.Board
import org.jetbrains.kotlinx.tictactoe.Game
import org.jetbrains.kotlinx.tictactoe.GameState
import org.jetbrains.kotlinx.tictactoe.RandomPlayer
import org.jetbrains.kotlinx.tictactoe.MinimaxPlayer

fun main() {
    println("--- Welcome to Kotlin Tic-Tac-Toe! ---")

    // Setup players
    val playerX = setupPlayer(Mark.X)
    val playerO = setupPlayer(Mark.O)

    val game = Game(playerX, playerO)

    // Print initial board
    println("\nLet's begin! ${playerX.name} (X) vs ${playerO.name} (O).")
    printBoard(game.board)

    // Game Loop
    while (game.gameState is GameState.Ongoing) {
        val currentPlayerMark = (game.gameState as GameState.Ongoing).currentPlayer
        val currentPlayerName = if (currentPlayerMark == Mark.X) playerX.name else playerO.name

        println("\n--- ${currentPlayerName}'s turn ($currentPlayerMark) ---")

        // Play a turn
        try {
            game.playTurn()
        } catch (e: Exception) {
            // This should not occur, it may if a Human player implementation provides a bad move
            println("An error occurred: ${e.message}. Skipping turn.")
        }

        // Print updated board
        printBoard(game.board)
    }

    // Print final result
    println("\n--- Game Over! ---")
    when (val finalState = game.gameState) {
        is GameState.Win -> {
            val winnerName = if (finalState.winner == Mark.X) playerX.name else playerO.name
            println("🏆 Congratulations, $winnerName (${finalState.winner}) wins!")
        }
        is GameState.Draw -> println("🤝 It's a draw! Good game.")
        is GameState.Ongoing -> { /* This should not happen */ }
    }
}

/**
 * Prints a user-friendly representation of the board to the console.
 * Empty spots are numbered 1-9.
 */
private fun printBoard(board: Board) {
    println()
    for (row in 0..2) {
        for (col in 0..2) {
            val index = row * 3 + col
            val mark = board.getMarkAt(index)
            // Show 1-9 for empty spots
            print(" ${mark ?: (index + 1)} ")
            if (col < 2) print("|")
        }
        println()
        if (row < 2) println("---+---+---")
    }
    println()
}

/**
 * Asks the user to configure a player (Human or Computer).
 */
private fun setupPlayer(mark: Mark): Player {
    println("\nConfiguring Player $mark:")

    val type = loopForInput("Enter '1' for Human or '2' for Computer: ", setOf("1", "2"))

    if (type == "1") {
        // Setup Human player
        print("Enter name for Human $mark (or press Enter for 'Human $mark'): ")

        val input = readlnOrNull()?.trim()
        val name = if (input.isNullOrEmpty()) "Human $mark" else input
        return CliHumanPlayer(name, mark)

    } else {
        // Setup Computer player
        println("Select AI difficulty for $mark:")
        val difficulty = loopForInput(
            "Enter '1' for Easy (Random) or '2' for Hard (Unbeatable): ",
            setOf("1", "2")
        )

        return if (difficulty == "1") {
            RandomPlayer("Easy AI ($mark)", mark)
        } else {
            MinimaxPlayer("Hard AI ($mark)", mark)
        }
    }
}

/**
 * A helper for robustly getting user input from a set of valid options.
 */
private fun loopForInput(prompt: String, validOptions: Set<String>): String {
    while (true) {
        print(prompt)
        val input = readlnOrNull()?.trim()

        if (input != null && input in validOptions) {
            return input
        }

        println("Invalid input. Please enter one of: ${validOptions.joinToString(", ")}")
    }
}

/**
 * An app-specific implementation of the Player interface.
 * This class lives in `app` because it deals with the console `readln`.
 */
class CliHumanPlayer(
    override val name: String,
    override val mark: Mark
) : Player {
    /**
     * asks for a move and validates it.
     */
    override fun getMove(board: Board): Int {
        while (true) {
            print("$name, enter your move (a number 1-9): ")
            try {
                val input = readlnOrNull()?.trim()
                val choice = input?.toInt()
                if (choice != null && choice in 1..9) {
                    val index = choice - 1 // Convert 1-9 to 0-8
                    if (index in board.availableMoves) {
                        return index // Valid move
                    } else {
                        println("That spot is already taken! Try again.")
                    }
                } else {
                    println("Invalid input. Please enter a number from 1 to 9.")
                }
            } catch (e: NumberFormatException) {
                println("Invalid input. That's not a number.")
            }
        }
    }
}