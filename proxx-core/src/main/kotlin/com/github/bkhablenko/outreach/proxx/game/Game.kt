package com.github.bkhablenko.outreach.proxx.game

import com.github.bkhablenko.outreach.proxx.arrays.Array2D
import com.github.bkhablenko.outreach.proxx.exception.GameOverException

/**
 * State of the game.
 */
class Game(private val board: GameBoard) {

    var isOver: Boolean = false
        private set

    var isLost: Boolean = false
        private set

    companion object {
        fun new(difficulty: GameDifficulty): Game {
            val board = with(difficulty) {
                GameBoard.squareShaped(boardSideLength, numBlackHoles)
            }
            return Game(board)
        }
    }

    val tiles: Array2D<Tile> get() = board.tiles

    /**
     * Reveal a tile on the board and update the game state.
     */
    fun revealTile(i: Int, j: Int) {
        if (isOver) {
            throw GameOverException()
        }
        val tile = board.revealTile(i, j)
        when {
            tile is BlackHoleTile -> {
                isOver = true
                isLost = true
            }

            board.safeTilesLeft == 0 -> isOver = true
        }
    }
}
