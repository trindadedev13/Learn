package dev.trindadedev.learn.ui.components.bounce

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import dev.trindadedev.learn.ui.components.base.BaseViewGroup

class BounceLayout
@JvmOverloads
constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : BaseViewGroup(context, attrs, defStyleAttr) {
  var events = BounceLayoutEvents()

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
