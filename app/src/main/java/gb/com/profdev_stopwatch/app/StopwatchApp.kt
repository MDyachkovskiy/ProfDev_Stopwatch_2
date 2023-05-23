package gb.com.profdev_stopwatch.app

import android.app.Application
import gb.com.profdev_stopwatch.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class StopwatchApp : Application() {

    override fun onCreate() {
        super.onCreate()
        
        startKoin{
            androidContext(this@StopwatchApp)
            modules(appModule)
        }
    }
}