package com.github.bkhablenko.outreach.proxx.game

enum class GameDifficulty(val boardSideLength: Int, val numBlackHoles: Int) {

    EASY(
        boardSideLength = 8,
        numBlackHoles = 10,
    ),
    MEDIUM(
        boardSideLength = 16,
        numBlackHoles = 40,
    ),
    HARD(
        boardSideLength = 24,
        numBlackHoles = 99,
    );

    companion object {
        fun ignoreCaseValueOf(name: String): GameDifficulty? {
            return values().firstOrNull {
                name.equals(it.name, ignoreCase = true)
            }
        }
    }
}
