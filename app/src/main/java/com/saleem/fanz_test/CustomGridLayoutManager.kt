package com.saleem.fanz_test

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

class CustomGridLayoutManager(
    context: Context,
    private val columnSizes: IntArray
) : GridLayoutManager(context, 1) {

    private var maxSpans = 0

    init {
        calculateMaxSpans()
        spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                var currentPosition = 0
                for (columnSize in columnSizes) {
                    if (position >= currentPosition && position < currentPosition + columnSize) {
                        return columnSize
                    }
                    currentPosition += columnSize
                }
                return 1
            }
        }
    }

    private fun calculateMaxSpans() {
        maxSpans = columnSizes.sum()
        setSpanCount(maxSpans)
    }
}