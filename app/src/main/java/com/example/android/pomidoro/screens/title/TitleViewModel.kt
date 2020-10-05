package com.example.android.pomidoro.screens.title

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class TitleViewModel: ViewModel() {

    companion object {

        // This is when the game is over
        const val DONE = 0L

        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L

        // Default time value
        const val DEFAULT_TIME = 30000L
    }


    private val timer: CountDownTimer

    // Time for task. Default value is 30 minutes
    private val _currentTime = MutableLiveData<Long>()
    private val currentTime: LiveData<Long>
        get() = _currentTime

    // Task state
    private val _taskState = MutableLiveData<Boolean>()
    val taskState: LiveData<Boolean>
        get() = _taskState

    // Transform time to string format for UI
    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    init {
        _currentTime.value = 30000L / 1000L
        _taskState.value = false

        timer = object : CountDownTimer(DEFAULT_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun startAction() {
        if (_taskState.value == false) {
            timer.start()
            _taskState.value = true
        } else {
            timer.cancel()
            _currentTime.value = 30000L / 1000L
            _taskState.value = false
        }
    }
}