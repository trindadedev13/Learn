package dev.trindadedev.learn.ui.theme

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Switch

import dev.trindadedev.learn.ui.components.drag.DragView

import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.theme.MaterialComponentsViewInflater

/**
 * class to continue Material Components Inflater
 */
class LearnViewInflater: MaterialComponentsViewInflater() {
  override fun createView(
    context: Context, 
    name: String, 
    attrs: AttributeSet
  ): View {
    return when (name) {
      "Switch" -> createSwitch(context, attrs)
      "DragView" -> createDragView(context, attrs)
      else -> View(context, attrs)
    }
  }
  
  protected fun createDragView(context: Context, attrs: AttributeSet): DragView {
    return DragView(context, attrs)
  }
  
  protected fun createSwitch(context: Context, attrs: AttributeSet): Switch {
    return MaterialSwitch(context, attrs)
  }
}
