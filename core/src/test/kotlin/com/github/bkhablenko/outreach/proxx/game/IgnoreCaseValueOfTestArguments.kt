package com.github.bkhablenko.outreach.proxx.game

import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream

/**
 * @see [GameDifficultyTest.IgnoreCaseValueOfTest]
 */
object IgnoreCaseValueOfTestArguments {

    @JvmStatic
    fun get(): Stream<Arguments> = Stream.of(
        Arguments.of("easy", GameDifficulty.EASY),
        Arguments.of("Easy", GameDifficulty.EASY),
        Arguments.of("EASY", GameDifficulty.EASY),
    )
}
