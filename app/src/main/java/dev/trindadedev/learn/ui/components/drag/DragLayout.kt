package dev.trindadedev.learn.ui.components.drag

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
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
  
  fun getChilds(): List<View> {
    val childs = mutableListOf<View>()
    for (pos in 0 until childCount) {
      childs.add(getChildAt(pos))
    }
    return childs.toList()
  }
  
  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    var left = 0
    var top = 0

    val childs = getChilds()
    childs.forEach { child ->
      if (child.visibility != View.GONE) {
        val width = child.measuredWidth
        val height = child.measuredHeight
        
        child.layout(left, top, left + width, top + height)
        left += width
      }
    }
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    var totalWidth = 0
    var maxHeight = 0

    val childs = getChilds()
    childs.forEach { child ->
      if (child.visibility != View.GONE) {
        measureChild(child, widthMeasureSpec, heightMeasureSpec)
        totalWidth += child.measuredWidth
        maxHeight = maxOf(maxHeight, child.measuredHeight)
      }
    }
    val width = resolveSize(totalWidth, widthMeasureSpec)
    val height = resolveSize(maxHeight, heightMeasureSpec)
    setMeasuredDimension(width, height)
  }
}