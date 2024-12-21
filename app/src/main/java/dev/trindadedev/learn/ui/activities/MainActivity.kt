package dev.trindadedev.learn.ui.activities

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import dev.trindadedev.learn.databinding.ActivityMainBinding
import dev.trindadedev.learn.ui.scale.Scale
import dev.trindadedev.learn.ui.scale.ScaleState

public class MainActivity : AppCompatActivity() {

  private var _binding: ActivityMainBinding? = null
  private val binding: ActivityMainBinding
    get() = checkNotNull(_binding) { "Activity has been destroyed" }

  private lateinit var scale: Scale

  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()
    super.onCreate(savedInstanceState)
    _binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    configureBounceLayout()
  }
  
  private fun configureBounceLayout() {
    val scaleState = ScaleState(view = binding.bounceLayout)
    scale = Scale(context = this, state = scaleState)
    
    binding.bounceLayout.events.state.initialBound = 100f 
    binding.bounceLayout.events.state.targetBound = resources.displayMetrics.heightPixels.toFloat()
    binding.bounceLayout.events.state.currentBound = binding.bounceLayout.events.state.initialBound
    binding.bounceLayout.post {
      binding.bounceLayout.layoutParams.height = binding.bounceLayout.events.state.initialBound.toInt()
      binding.bounceLayout.requestLayout()
    }
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    scale.onTouchEvent(event)
    return true
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }
}
