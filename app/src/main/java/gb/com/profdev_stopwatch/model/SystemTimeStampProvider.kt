package gb.com.profdev_stopwatch.model

class SystemTimeStampProvider : TimestampProvider {
    override fun getMilliseconds(): Long {
        return System.currentTimeMillis()
    }
}