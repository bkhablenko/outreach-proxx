package com.github.bkhablenko.outreach.proxx.game

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.nullValue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("GameDifficulty")
class GameDifficultyTest {

    @DisplayName("ignoreCaseValueOf")
    @Nested
    inner class IgnoreCaseValueOfTest {

        @ParameterizedTest
        @MethodSource("com.github.bkhablenko.outreach.proxx.game.IgnoreCaseValueOfTestArguments#get")
        fun `should return the expected value`(name: String, expectedValue: GameDifficulty) {
            assertThat(GameDifficulty.ignoreCaseValueOf(name), equalTo(expectedValue))
        }

        @Test
        fun `should return null if not found`() {
            val nonExistentName = "Hello, World!"
            assertThat(GameDifficulty.ignoreCaseValueOf(nonExistentName), nullValue())
        }
    }
}
