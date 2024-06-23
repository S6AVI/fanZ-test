package com.saleem.util

// turn a statement into an expression
val <T> T.exhaustive: T
    get() = this