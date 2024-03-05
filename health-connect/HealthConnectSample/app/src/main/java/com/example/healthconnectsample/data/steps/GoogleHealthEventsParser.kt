package com.example.healthconnectsample.data.steps

import androidx.health.connect.client.aggregate.AggregationResultGroupedByDuration
import androidx.health.connect.client.records.ExerciseSessionRecord
import androidx.health.connect.client.records.StepsRecord
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.Temporal

object GoogleHealthEventsParser {

    fun getExerciseEventInputs(aggregations: List<AggregationResultGroupedByDuration>): List<StepRecordResult> {
        val apiEvents = mutableListOf<StepRecordResult>()

        aggregations.forEach { aggregation ->
            apiEvents.add(getExerciseEventFromStepRecord(aggregation.getStepsCount(), aggregation))
        }
        return apiEvents
    }

    private fun getExerciseEventFromStepRecord(
        quantity: Int,
        result: AggregationResultGroupedByDuration,
    ): StepRecordResult {
        return StepRecordResult(
            startedAt = getTemporal(result.startTime),
            endedAt = getTemporal(result.endTime),
            quantity = quantity,
        )
    }

    private fun AggregationResultGroupedByDuration.getStepsCount() = this.result[StepsRecord.COUNT_TOTAL]?.toInt() ?: 0

    private fun getTemporal(instant: Instant): Temporal {
        return ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"))
    }
}