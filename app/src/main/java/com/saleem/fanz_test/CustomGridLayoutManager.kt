package com.saleem.fanz_test

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

class CustomGridLayoutManager(
    context: Context,
    private val columnSizes: IntArray
) : GridLayoutManager(context, columnSizes.maxOrNull() ?: 1) {

    init {
        spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                var currentPosition = 0
                var currentRowSpanCount = 0
                for (columnSize in columnSizes) {
                    currentRowSpanCount = columnSize
                    if (position >= currentPosition && position < currentPosition + columnSize) {
                        return calculateSpanSize(position - currentPosition, currentRowSpanCount)
                    }
                    currentPosition += columnSize
                }
                return 1
            }
        }
    }

    private fun calculateSpanSize(positionInRow: Int, rowSpanCount: Int): Int {
        return if (rowSpanCount == 0) {
            spanCount
        } else {

            val spanSize = spanCount / rowSpanCount
            if (positionInRow < spanCount % rowSpanCount){
                spanSize + 1
            } else {
                spanSize
            }
        }
    }
}