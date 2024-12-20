package dev.trindadedev.learn.ui.scale

import android.view.View

data class ScaleState(
  val view: View,
  var scaleFactor: Float = 1.0f,
  var scaleFactorMin: Float = 0.5f,
  var scaleFactorMax: Float = 3.0f
)