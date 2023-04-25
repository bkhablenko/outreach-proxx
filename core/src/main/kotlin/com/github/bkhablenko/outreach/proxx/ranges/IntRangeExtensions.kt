package com.github.bkhablenko.outreach.proxx.ranges

/**
 * @return the Cartesian product of two integer ranges [this] and [other].
 */
operator fun IntRange.times(other: IntRange) = flatMap { first -> other.map { second -> first to second } }
