package gb.com.profdev_stopwatch.repository

import gb.com.profdev_stopwatch.model.StopwatchStateHolder

class StopwatchUseCase(
    private val stopwatchStateHolder: StopwatchStateHolder
) {

    fun start() {
        stopwatchStateHolder.start()
    }

    fun pause() {
        stopwatchStateHolder.pause()
    }

    fun stop() {
        stopwatchStateHolder.stop()
    }

    fun getStringTimeRepresentation(): String {
        return stopwatchStateHolder.getStringTimeRepresentation()
    }
}