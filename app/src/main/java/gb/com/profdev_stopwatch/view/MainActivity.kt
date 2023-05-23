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

        model.ticker.observe(this) {
            textView.text = it
        }

        with(binding){
            buttonStart.setOnClickListener {
                model.start()
            }

            buttonPause.setOnClickListener {
                model.pause()
            }

            buttonStop.setOnClickListener {
                model.stop()
            }
        }
    }
}