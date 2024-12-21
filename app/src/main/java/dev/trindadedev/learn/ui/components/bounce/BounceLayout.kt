package dev.trindadedev.learn.ui.components.bounce

import android.content.Context
import android.util.AttributeSet
import android.MotionEvent
import dev.trindadedev.learn.ui.components.base.BaseViewGroup

class BounceLayout
@JvmOverloads
constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : BaseViewGroup(context, attrs, defStyleAttr) {
  var events = BounceLayoutEvents(context)

  init {
    events.state.initialBound = 100f 
    events.state.targetBound = resources.displayMetrics.heightPixels.toFloat()
    events.state.currentBound = events.state.initialBound
    layoutParams.height = events.state.initialBound.toInt()
    requestLayout()
    
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
