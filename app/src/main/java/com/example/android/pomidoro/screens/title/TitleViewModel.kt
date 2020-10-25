package com.example.android.pomidoro.screens.title

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
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
//
        // Default time value
        const val DEFAULT_TIME = 1500000L

        // This is the number of milliseconds in a minutes
        const val ONE_MINUTES = 60000L
    }

    private val _goalText = MutableLiveData<String>()
    val goalText: LiveData<String>
        get() = _goalText

    private val _actionButtonText = MutableLiveData<String>()
    val actionButtonText: LiveData<String>
        get() = _actionButtonText

    private var _defaultTime = MutableLiveData<Long>()

    private lateinit var timer: CountDownTimer

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
        Log.i("ViewModel", "ViewModel Init")
        _currentTime.value = DEFAULT_TIME / ONE_SECOND
        _actionButtonText.value = "Поехали"
        _goalText.value = "Выберите цель"
        _taskState.value = false
        _defaultTime.value = DEFAULT_TIME

        createTimer(_defaultTime.value!!)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TitleViewModel", "Cancelled")
        timer.cancel()
    }

    private fun createTimer(fullTime: Long) {
        timer = object : CountDownTimer(fullTime, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE
            }
        }
    }

    fun startAction() {
        if (_taskState.value == false) {
            createTimer(_defaultTime.value!!)
            timer.start()
            _actionButtonText.value = "Сдаешься?"
            _taskState.value = true
        } else {
            timer.cancel()
            _currentTime.value = _defaultTime.value!! / ONE_SECOND
            _actionButtonText.value = "Поехали"
            _taskState.value = false
        }
    }

    fun setTime(minutes: Long) {
        Log.i("Minutes", "$minutes")
        _defaultTime.value = minutes * ONE_MINUTES
        _currentTime.value = _defaultTime.value!! / ONE_SECOND
    }

    fun setGoal(text: String) {
        _goalText.value = text
    }
}