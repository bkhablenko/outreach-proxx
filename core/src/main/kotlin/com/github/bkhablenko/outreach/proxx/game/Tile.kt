package com.github.bkhablenko.outreach.proxx.game

/**
 * A single tile on the game board.
 */
sealed class Tile {

    var isRevealed: Boolean = false
        private set

    fun reveal() {
        isRevealed = true
    }
}

class BlankTile : Tile() {

    companion object {
        // https://en.wikipedia.org/wiki/Interpunct
        private const val INTERPUNCT = "\u00B7"
    }

    override fun toString() = if (isRevealed) " " else INTERPUNCT
}

class NumberTile(val blackHolesNearby: Int) : Tile() {

    override fun toString() = "$blackHolesNearby"
}

class BlackHoleTile : Tile() {

    override fun toString() = "*"
}
