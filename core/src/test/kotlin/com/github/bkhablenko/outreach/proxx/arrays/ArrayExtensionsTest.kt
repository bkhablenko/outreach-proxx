package com.github.bkhablenko.outreach.proxx.arrays

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("ArrayExtensions")
class ArrayExtensionsTest {

    companion object {

        private const val N = 3
        private const val M = 5
    }

    @DisplayName("get")
    @Nested
    inner class GetTest {

        @Test
        fun `should return the expected value`() {
            val array = Array(N) { Array(M) { 0 } }

            val i = 0
            val j = 0

            val expectedValue = 100
            array[i][j] = expectedValue

            assertThat(array[i to j], equalTo(expectedValue))
        }
    }

    @DisplayName("set")
    @Nested
    inner class SetTest {

        @Test
        fun `should update the array`() {
            val array = Array(N) { Array(M) { 0 } }

            val i = 0
            val j = 0

            val expectedValue = 100
            array[i to j] = expectedValue

            assertThat(array[i][j], equalTo(expectedValue))
        }
    }
}

