package gb.com.profdev_stopwatch.di

import gb.com.profdev_stopwatch.appState.StopwatchStateCalculator
import gb.com.profdev_stopwatch.model.ElapsedTimeCalculator
import gb.com.profdev_stopwatch.model.StopwatchStateHolder
import gb.com.profdev_stopwatch.model.SystemTimeStampProvider
import gb.com.profdev_stopwatch.model.TimestampProvider
import gb.com.profdev_stopwatch.repository.StopwatchUseCase
import gb.com.profdev_stopwatch.repository.TimestampMillisecondsFormatter
import gb.com.profdev_stopwatch.view.StopwatchViewModel
import org.koin.dsl.module

val appModule = module {
    single <TimestampProvider> {SystemTimeStampProvider()}
    single {TimestampMillisecondsFormatter()}
    single {StopwatchStateCalculator(get(), get())}
    single {ElapsedTimeCalculator(get())}
    factory {StopwatchStateHolder(get(), get(), get())}
    factory {StopwatchUseCase(get())}
    factory {StopwatchViewModel(get(), get())}
}