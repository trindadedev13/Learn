package dev.trindadedev.learn.ui.components.drag

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DragView @JvmOverloads
constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
  var events = DragViewEvents(context)

  init {
    setOnTouchListener { view, event ->
      when (event.action) {
        MotionEvent.ACTION_UP -> events.onUp(view, event)
        MotionEvent.ACTION_DOWN -> events.onDown(view, event)
        MotionEvent.ACTION_MOVE -> events.onMove(view, event)
        else -> false
      }
      true
    }
  }
}