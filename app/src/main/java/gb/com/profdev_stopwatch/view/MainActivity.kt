package gb.com.profdev_stopwatch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import gb.com.profdev_stopwatch.R
import gb.com.profdev_stopwatch.model.ElapsedTimeCalculator
import gb.com.profdev_stopwatch.appState.StopwatchStateCalculator
import gb.com.profdev_stopwatch.model.StopwatchStateHolder
import gb.com.profdev_stopwatch.model.SystemTimeStampProvider
import gb.com.profdev_stopwatch.repository.TimestampMillisecondsFormatter
import gb.com.profdev_stopwatch.repository.StopwatchUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: StopwatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = StopwatchViewModel(
            StopwatchUseCase(
                StopwatchStateHolder(
                    StopwatchStateCalculator(
                        SystemTimeStampProvider(),
                        ElapsedTimeCalculator(SystemTimeStampProvider())
                    ),
                    ElapsedTimeCalculator(SystemTimeStampProvider()),
                    TimestampMillisecondsFormatter()
                )
            ),
            CoroutineScope(
                Dispatchers.Main + SupervisorJob()
            )
        )

        val textView = findViewById<TextView>(R.id.text_time)

        viewModel.ticker.observe(this) {
            textView.text = it
        }

        findViewById<Button>(R.id.button_start).setOnClickListener {
            viewModel.start()
        }

        findViewById<Button>(R.id.button_pause).setOnClickListener {
            viewModel.pause()
        }

        findViewById<Button>(R.id.button_stop).setOnClickListener {
            viewModel.stop()
        }
    }
}