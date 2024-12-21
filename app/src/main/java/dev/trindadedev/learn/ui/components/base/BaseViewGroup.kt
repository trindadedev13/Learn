package dev.trindadedev.learn.ui.components.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

abstract class BaseViewGroup
@JvmOverloads
constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

  /** make a for in chilcount, get child at position and add a list */
  protected fun getChilds(): List<View> {
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
