package gb.com.profdev_stopwatch.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gb.com.profdev_stopwatch.repository.StopwatchUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class StopwatchViewModel(
    private val stopwatchUseCaseFirst: StopwatchUseCase,
    private val stopwatchUseCaseSecond: StopwatchUseCase
) : ViewModel() {

    private var jobFirst: Job? = null
    private var jobSecond: Job? = null

    private val _tickerFirst = MutableLiveData<String>()
    val tickerFirst: LiveData<String> get() = _tickerFirst

    private val _tickerSecond = MutableLiveData<String>()
    val tickerSecond: LiveData<String> get() = _tickerSecond

    fun startFirst() {
        if (jobFirst == null) startJobFirst()
        stopwatchUseCaseFirst.start()
    }

    fun startSecond() {
        if (jobSecond == null) startJobSecond()
        stopwatchUseCaseSecond.start()
    }

    private fun startJobFirst() {
        jobFirst = viewModelScope.launch{
            while (isActive) {
                _tickerFirst.value = stopwatchUseCaseFirst.getStringTimeRepresentation()
                delay(20)
            }
        }
    }

    private fun startJobSecond() {
        jobSecond = viewModelScope.launch {
            while (isActive) {
                _tickerSecond.value = stopwatchUseCaseSecond.getStringTimeRepresentation()
                delay(20)
            }
        }
    }

    fun pauseFirst() {
        stopwatchUseCaseFirst.pause()
        stopJobFirst()
    }

    fun pauseSecond() {
        stopwatchUseCaseSecond.pause()
        stopJobSecond()
    }

    fun stopFirst() {
        stopwatchUseCaseFirst.stop()
        stopJobFirst()
        clearValueFirst()
    }

    fun stopSecond() {
        stopwatchUseCaseSecond.stop()
        stopJobSecond()
        clearValueSecond()
    }

    private fun stopJobFirst(){
        jobFirst?.cancel()
        jobFirst = null
    }

    private fun stopJobSecond(){
        jobSecond?.cancel()
        jobSecond = null
    }

    private fun clearValueFirst() {
        _tickerFirst.value = DEFAULT_TIME
    }

    private fun clearValueSecond() {
        _tickerSecond.value = DEFAULT_TIME
    }

    companion object {
        const val DEFAULT_TIME = "00.00.000"
    }
}
