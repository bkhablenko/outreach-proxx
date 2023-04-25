package com.github.bkhablenko.outreach.proxx.terminal

import com.github.ajalt.mordant.rendering.TextColors.brightRed
import com.github.ajalt.mordant.rendering.TextColors.brightWhite
import com.github.ajalt.mordant.rendering.TextColors.gray
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.rendering.TextStyles.bold
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.ConversionResult
import com.github.ajalt.mordant.terminal.ExperimentalTerminalApi
import com.github.ajalt.mordant.terminal.Terminal
import com.github.bkhablenko.outreach.proxx.arrays.IndexPair
import com.github.bkhablenko.outreach.proxx.game.BlackHoleTile
import com.github.bkhablenko.outreach.proxx.game.BlankTile
import com.github.bkhablenko.outreach.proxx.game.Game
import com.github.bkhablenko.outreach.proxx.game.GameDifficulty
import com.github.bkhablenko.outreach.proxx.game.NumberTile
import com.github.bkhablenko.outreach.proxx.game.Tile

/**
 * Controls the game flow through the terminal.
 */
class TerminalController private constructor() {

    companion object {
        private const val DEFAULT_PROMPT_SUFFIX = "\n> "

        private val DEFAULT_TEXT_STYLE = TextStyle(color = brightWhite.color)

        private val INVALID_INPUT =
            ConversionResult.Invalid("Your input doesn't seem right.")

        fun launch() {
            TerminalController().run()
        }
    }

    @OptIn(ExperimentalTerminalApi::class)
    private val terminal = Terminal()

    private lateinit var game: Game

    private fun run() {
        game = Game.new(promptGameDifficulty())
        printGameBoard()

        while (!game.isOver) {
            val (i, j) = promptIndexPair()
            revealTile(i, j)

            printGameBoard()
        }

        if (game.isLost) {
            terminal.danger("You have lost!")
        } else {
            terminal.success("Congratulations! You have won!")
        }
    }

    private fun revealTile(i: Int, j: Int) {
        try {
            game.revealTile(i, j)
        } catch (_: Exception) {
            terminal.danger("That's an illegal move. You can't do that.")
        }
    }

    private fun promptGameDifficulty(): GameDifficulty {
        val prompt = "What difficulty should we play on?".styled()
        return terminal.prompt(
            prompt,
            choices = GameDifficulty.values().toList(),
            promptSuffix = DEFAULT_PROMPT_SUFFIX,
        ) { input ->
            GameDifficulty.ignoreCaseValueOf(input)?.let { ConversionResult.Valid(it) } ?: INVALID_INPUT
        }!! // Can it ever be null?
    }

    private fun promptIndexPair(): IndexPair {
        val prompt = "Which tile to reveal next?".styled()
        return terminal.prompt(prompt, promptSuffix = DEFAULT_PROMPT_SUFFIX) { input ->
            try {
                ConversionResult.Valid(parseIndexPair(input))
            } catch (_: Exception) {
                INVALID_INPUT
            }
        }!! // Can it ever be null?
    }

    private fun parseIndexPair(input: String): IndexPair {
        return input.split(" ", limit = 2).map { it.toInt() }.let { it[0] to it[1] }
    }

    private fun printGameBoard() {
        val tiles = game.tiles.map { row -> row.map { tile -> applyStyle(tile) } }

        terminal.println(table {
            body {
                tiles.forEach { row -> row(*row.toTypedArray()) }
            }
        })
    }

    private fun applyStyle(tile: Tile): String {
        return when (tile) {
            is BlankTile -> gray("$tile")
            is NumberTile -> bold(brightWhite("${tile.blackHolesNearby}"))
            is BlackHoleTile -> bold(brightRed("$tile"))
        }
    }

    private fun String.styled() = DEFAULT_TEXT_STYLE(this)
}
