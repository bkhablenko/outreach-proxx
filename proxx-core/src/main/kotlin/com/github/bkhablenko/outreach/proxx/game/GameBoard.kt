package com.github.bkhablenko.outreach.proxx.game

import com.github.bkhablenko.outreach.proxx.arrays.Array2D
import com.github.bkhablenko.outreach.proxx.arrays.IndexPair
import com.github.bkhablenko.outreach.proxx.arrays.get
import com.github.bkhablenko.outreach.proxx.arrays.set
import com.github.bkhablenko.outreach.proxx.collections.takeShuffled
import com.github.bkhablenko.outreach.proxx.exception.InvalidIndexPairException
import com.github.bkhablenko.outreach.proxx.exception.TileAlreadyRevealedException
import com.github.bkhablenko.outreach.proxx.ranges.times

class GameBoard private constructor(val width: Int, val height: Int, val numBlackHoles: Int) {

    private val _tiles: Array2D<Tile> = Array(height) { Array(width) { BlankTile() } }

    /**
     * All possible pairs of indices that can be used to address the array of [_tiles].
     */
    private val validIndexPairs = (0 until height) * (0 until width)

    companion object {
        fun squareShaped(sideLength: Int, numBlackHoles: Int) = GameBoard(sideLength, sideLength, numBlackHoles)
    }

    init {
        // Initialize the array of tiles in constant time with no duplicates
        validIndexPairs
            .takeShuffled(numBlackHoles)
            .forEach {
                placeBlackHole(it)
            }
    }

    /**
     * Copy of the internal state with all unrevealed tiles replaced with a [BlankTile].
     */
    val tiles: Array2D<Tile>
        get() {
            val tiles = _tiles.map { row -> row.copyOf() }.toTypedArray()

            // Include revealed tiles only
            validIndexPairs.filter { !tiles[it].isRevealed }.forEach { tiles[it] = BlankTile() }

            return tiles
        }

    /**
     * Shows how many safe tiles are left to reveal.
     */
    var safeTilesLeft = validIndexPairs.size - numBlackHoles
        private set

    /**
     * Reveal a specific [Tile] on the game board.
     *
     * @return the revealed [Tile].
     */
    fun revealTile(i: Int, j: Int): Tile {
        val indexPair = i to j

        if (!indexPair.isValid) {
            throw InvalidIndexPairException()
        }
        return reveal(linkedSetOf(indexPair))
    }

    /**
     * Reveal safe tiles in breadth-first order starting from the head of the [queue].
     */
    private fun reveal(queue: LinkedHashSet<IndexPair>): Tile {
        val head = queue.first().also { queue.remove(it) }

        val tile = _tiles[head]
        if (tile.isRevealed) {
            throw TileAlreadyRevealedException()
        }
        tile.reveal()

        safeTilesLeft--

        if (tile is BlankTile) {
            // Reveal safe tiles nearby
            head.nearby.filter { !_tiles[it].isRevealed }.forEach { queue += it }
        }
        if (queue.isNotEmpty()) reveal(queue)

        return tile
    }

    /**
     * Place a [BlackHoleTile] at the given [location] and update the surrounding tiles.
     */
    private fun placeBlackHole(location: IndexPair) {
        _tiles[location] = BlackHoleTile()

        location.nearby
            .filter {
                // Do not overwrite black holes
                _tiles[it] !is BlackHoleTile
            }
            .forEach {
                val tile = _tiles[it]
                val blackHolesNearby = if (tile is NumberTile) tile.blackHolesNearby else 0
                _tiles[it] = NumberTile(blackHolesNearby + 1)
            }
    }

    private val IndexPair.nearby: List<IndexPair>
        get() = let { (i, j) ->
            listOf(
                i - 1 to j - 1,
                i - 1 to j,
                i - 1 to j + 1,

                i to j - 1,
                i to j + 1,

                i + 1 to j - 1,
                i + 1 to j,
                i + 1 to j + 1,
            ).filter {
                it.isValid
            }
        }

    /**
     * Shows whether this pair of indices can be used to address the array of [_tiles].
     */
    private val IndexPair.isValid: Boolean get() = this in validIndexPairs
}
