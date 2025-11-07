package org.jetbrains.kotlinx.tictactoe

enum class Mark {
    X, O;

    val opponent: Mark
        get() = if (this == X) O else X
}