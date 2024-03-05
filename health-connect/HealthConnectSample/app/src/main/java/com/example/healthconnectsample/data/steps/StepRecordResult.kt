package com.example.healthconnectsample.data.steps

import java.time.temporal.Temporal

data class StepRecordResult(val startedAt: Temporal, val endedAt: Temporal, val quantity: Int)