package gb.com.profdev_stopwatch.model

interface TimestampProvider {
    fun getMilliseconds(): Long
}