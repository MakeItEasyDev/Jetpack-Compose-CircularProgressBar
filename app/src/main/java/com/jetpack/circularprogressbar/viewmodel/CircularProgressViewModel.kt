package com.jetpack.circularprogressbar.viewmodel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CircularProgressViewModel: ViewModel() {
    private var decreaseTimer: CountDownTimer? = null
    private var increaseTimer: CountDownTimer? = null
    private var decreaseSecond: Int = 30
    private var increaseSecond: Int = 1
    private val _decreaseSecond = MutableLiveData(0)
    val decreaseSec: LiveData<Int> get() = _decreaseSecond
    private val _increaseSecond = MutableLiveData(0)
    val increaseSec: LiveData<Int> get() = _increaseSecond

    fun decreaseCountDown(totalSec: Long) {
        decreaseTimer = object : CountDownTimer(totalSec, 1000) {
            override fun onTick(milliSec: Long) {
                _decreaseSecond.value = decreaseSecond
                decreaseSecond--
            }

            override fun onFinish() {
                Log.d("TAG", "Decrease Time finished")
            }
        }

        decreaseTimer?.start()
    }

    fun increaseCountDown(totalSec: Long) {
        increaseTimer = object : CountDownTimer(totalSec, 1000) {
            override fun onTick(milliSec: Long) {
                _increaseSecond.value = increaseSecond
                increaseSecond++
            }

            override fun onFinish() {
                Log.d("TAG", "Increase Time finished")
            }
        }

        increaseTimer?.start()
    }
}










