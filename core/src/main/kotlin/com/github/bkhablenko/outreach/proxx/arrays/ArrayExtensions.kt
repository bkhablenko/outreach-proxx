package com.github.bkhablenko.outreach.proxx.arrays

typealias Array2D<T> = Array<Array<T>>

typealias IndexPair = Pair<Int, Int>

operator fun <T> Array2D<T>.get(indexPair: IndexPair): T {
    return indexPair.let { (i, j) -> this[i][j] }
}

operator fun <T> Array2D<T>.set(indexPair: IndexPair, value: T) {
    indexPair.let { (i, j) -> this[i][j] = value }
}
