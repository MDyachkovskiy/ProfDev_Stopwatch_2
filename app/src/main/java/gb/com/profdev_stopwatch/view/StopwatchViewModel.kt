package gb.com.profdev_stopwatch.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gb.com.profdev_stopwatch.repository.StopwatchUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class StopwatchViewModel(
    private val stopwatchUseCase: StopwatchUseCase,
    private val scope: CoroutineScope
) : ViewModel() {

    private var job: Job? = null
    private val _ticker = MutableLiveData<String>()
    val ticker: LiveData<String> get() = _ticker

    fun start() {
        if (job == null) startJob()
        stopwatchUseCase.start()
    }

    private fun startJob() {
        scope.launch{
            while (isActive) {
                _ticker.value = stopwatchUseCase.getStringTimeRepresentation()
                delay(20)
            }
        }
    }

    fun pause() {
        stopwatchUseCase.pause()
        stopJob()
    }

    fun stop() {
        stopwatchUseCase.stop()
        stopJob()
        clearValue()
    }

    private fun stopJob(){
        scope.coroutineContext.cancelChildren()
        job = null
    }

    private fun clearValue() {
        _ticker.value = "00.00.000"
    }
}