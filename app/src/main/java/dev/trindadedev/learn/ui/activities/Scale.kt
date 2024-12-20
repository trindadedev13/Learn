package dev.trindadedev.learn.ui.activities

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector

class Scale(
  private val context: Context,
  private val state: ScaleState
) {
  private lateinit var scaleGestureDetector: ScaleGestureDetector
  
  init {
    scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())
  }
  
  public fun onTouchEvent(event: MotionEvent): Boolean {
    scaleGestureDetector.onTouchEvent(event)
    return true
  }
  
  private inner class ScaleListener: ScaleGestureDetector.SimpleOnScaleGestureListener() {
    override fun onScale(detector: ScaleGestureDetector): Boolean {
      state.scaleFactor *= detector.scaleFactor
      state.scaleFactor = state.scaleFactor.coerceIn(state.scaleFactorMin, state.scaleFactorMax)
      
      state.view.scaleX = state.scaleFactor
      state.view.scaleY = state.scaleFactor
      return true
    }
  }
}