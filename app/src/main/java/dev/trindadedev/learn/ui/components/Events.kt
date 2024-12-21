package dev.trindadedev.ui.components

import android.view.MotionEvent
import android.view.View

interface Events {
  fun onUp(view: View, event: MotionEvent)
  fun onDown(view: View, event: MotionEvent)
  fun onMove(view: View, event: MotionEvent)
}