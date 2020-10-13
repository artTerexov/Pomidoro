package com.example.android.pomidoro.screens.bottomSheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BottomSheetViewModelFactory(
    private val timeInterval: Long) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BottomSheetViewModel::class.java)) {
            return BottomSheetViewModel(timeInterval) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}