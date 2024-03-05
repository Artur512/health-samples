package com.example.healthconnectsample.data.steps

import java.time.Instant
import java.time.temporal.TemporalUnit

object TimeRangeSlicer {
    fun splitTimeRangeByInterval(start: Instant, end: Instant, amountToAdd: Long, unit: TemporalUnit): List<Pair<Instant, Instant>> {
        val ranges = mutableListOf<Pair<Instant, Instant>>()
        var startRange = start
        while (startRange < end) {
            val endRange = minOf(startRange.plus(amountToAdd, unit), end)
            ranges.add(startRange to endRange)
            startRange = endRange
        }
        return ranges
    }
}