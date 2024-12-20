package dev.trindadedev.learn.ui.components.drag

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import dev.trindadedev.learn.utils.vibrate

class DragViewEvents(
  private val context: Context,
  private val state: DragViewState = DragViewState()
) {
  
  fun onUp(view: View, event: MotionEvent) {
    //todo
  }
  
  fun onDown(view: View, event: MotionEvent) {
    state.dX = view.getX() - event.getRawX()
    state.dY = view.getY() - event.getRawY()
    context.vibrate(100)
  }
  
  fun onMove(view: View, event: MotionEvent) {
    val parent = view.parent as ViewGroup
    val newX = (event.rawX + state.dX).coerceIn(0f, parent.width - view.width.toFloat())
    val newY = (event.rawY + state.dY).coerceIn(0f, parent.height - view.height.toFloat())

    view.animate()
      .x(newX)
      .y(newY)
      .setDuration(state.duration)
      .start()
  }
}