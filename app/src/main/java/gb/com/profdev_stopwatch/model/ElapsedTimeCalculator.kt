package gb.com.profdev_stopwatch.model

import gb.com.profdev_stopwatch.appState.StopwatchState

class ElapsedTimeCalculator(
    private val timestampProvider: TimestampProvider
) {

    fun calculate (state: StopwatchState.Running): Long {
        val currentTimestamp = timestampProvider.getMilliseconds()
        val timePassedSinceStart =
            if (currentTimestamp > state.startTime){
                currentTimestamp - state.startTime
            } else {
                0
            }
        return timePassedSinceStart + state.elapsedTime
    }
}