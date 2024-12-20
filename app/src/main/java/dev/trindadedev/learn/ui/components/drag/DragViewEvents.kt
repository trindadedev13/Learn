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
    val parent = view.parent as ViewGroup
    val centerX = (parent.width - view.width) / 2f
    val centerY = (parent.height - view.height) / 2f

    view.animate()
      .x(centerX)
      .y(centerY)
      .setDuration(300)
      .withEndAction {
        view.layout(0, 0, parent.width, parent.height)
        view.animate()
          .x(0f)
          .y(0f)
          .setDuration(300)
          .start()
      }
      .start()
  }

  fun onDown(view: View, event: MotionEvent) {
    state.dX = view.x - event.rawX
    state.dY = view.y - event.rawY
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