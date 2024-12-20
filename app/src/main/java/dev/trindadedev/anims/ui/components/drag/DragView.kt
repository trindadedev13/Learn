package dev.trindadedev.anims.ui.components.drag

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DragView @JvmOverloads 
constructor(
  context: Context,
  attrs: AttributeSet? = null, 
  defStyleAttr: Int = -1
) : View(context, attrs, defStyleAttr) {
  
  var state = DragState()
  
  init {
    setOnTouchListener { view, event ->
      when (event.action) {
        MotionEvent.ACTION_DOWN -> onDown(view, event)
        MotionEvent.ACTION_MOVE -> onMove(view, event)
        else -> false
      }
      return true
    }
  }
  
  private fun onDown(view: View, event: MotionEvent) {
    state.dX = view.getX() - event.getRawX()
    state.dY = view.getY() - event.getRawY()
  }
  
  private fun onMove(view: View, event: MotionEvent) {
    view.animate()
      .x(event.getRawX() + state.dX)
      .y(event.getRawY() + state.dY)
      .setDuration(state.duration)
      .start()
  }
}