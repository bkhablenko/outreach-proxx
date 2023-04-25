package com.github.bkhablenko.outreach.proxx.collections

fun <T> Iterable<T>.takeShuffled(n: Int) = shuffled().take(n)
