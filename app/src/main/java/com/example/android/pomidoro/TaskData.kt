package com.example.android.pomidoro

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TaskData (
    val goal: String = "Выбери цель",
    val time: Long = 1500000L,
    val tomatoState: Int = 0
) : Parcelable