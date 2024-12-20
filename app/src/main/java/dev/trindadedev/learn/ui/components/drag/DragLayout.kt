package dev.trindadedev.learn.ui.components.drag

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup

class DragLayout @JvmOverloads
constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
  var events = DragLayoutEvents(context)

  init {
    setOnTouchListener { view, event ->
      when (event.action) {
        MotionEvent.ACTION_UP -> events.onUp(view, event)
        MotionEvent.ACTION_DOWN -> events.onDown(view, event)
        MotionEvent.ACTION_MOVE -> events.onMove(view, event)
      }
      true
    }
  }
}