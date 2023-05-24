package gb.com.profdev_stopwatch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import gb.com.profdev_stopwatch.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val model: StopwatchViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = binding.textTime
        val textViewSecond = binding.textTimeSecond

        model.tickerFirst.observe(this) {
            textView.text = it
        }

        model.tickerSecond.observe(this){
            textViewSecond.text = it
        }

        with(binding){
            buttonStart.setOnClickListener {
                model.startFirst()
            }

            buttonStartSecond.setOnClickListener {
                model.startSecond()
            }

            buttonPause.setOnClickListener {
                model.pauseFirst()
            }
            buttonPauseSecond.setOnClickListener {
                model.pauseSecond()
            }

            buttonStop.setOnClickListener {
                model.stopFirst()
            }

            buttonStopSecond.setOnClickListener {
                model.stopSecond()
            }
        }
    }
}