package dev.trindadedev.learn.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import dev.trindadedev.learn.databinding.ActivityMainBinding

public class MainActivity : AppCompatActivity() {
  
  private var _binding: ActivityMainBinding? = null
  private val binding: ActivityMainBinding
    get() = checkNotNull(_binding) { "Activity has been destroyed" }
  
  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()
    super.onCreate(savedInstanceState)
    _binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }
  
  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }
}