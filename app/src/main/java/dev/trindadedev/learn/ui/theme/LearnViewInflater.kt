package dev.trindadedev.learn.ui.theme

import android.content.Context
import android.util.AttributeSet
import android.view.View
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
      "Switch" -> MaterialSwitch(context, attrs)
      else -> createComponent(context, name, attrs)
    }
  }
  
  /**
   * Override this method in your class to continue inflater (if you want)
   */
  fun createComponent(
    context: Context, 
    name: String, 
    attrs: AttributeSet
  ): View {
    return null
  }
}
