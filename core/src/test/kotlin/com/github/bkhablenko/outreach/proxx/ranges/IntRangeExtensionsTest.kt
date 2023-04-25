package com.github.bkhablenko.outreach.proxx.ranges

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("IntRangeExtensions")
class IntRangeExtensionsTest {

    @DisplayName("times")
    @Nested
    inner class TimesTest {

        @Test
        fun `should return expected integer pairs`() {
            val product = (1..2) * (2..4)

            val expectedPairs = arrayOf(
                1 to 2,
                1 to 3,
                1 to 4,
                2 to 2,
                2 to 3,
                2 to 4,
            )
            assertThat(product, contains(*expectedPairs))
        }
    }
}
