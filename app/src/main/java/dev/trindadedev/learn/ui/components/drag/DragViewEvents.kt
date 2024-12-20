package dev.trindadedev.learn.ui.components.drag

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.animation.ObjectAnimator
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import dev.trindadedev.learn.utils.vibrate

class DragViewEvents(
  private val context: Context,
  private val state: DragViewState = DragViewState()
) {

  fun onUp(view: View, event: MotionEvent) {
    val parent = view.parent as ViewGroup
    val centerX = (parent.width - view.width) / 2f
    val centerY = (parent.height - view.height) / 2f

    val elasticInterpolator = OvershootInterpolator(1.5f)

    val centerXAnimator = ObjectAnimator.ofFloat(view, "x", view.x, centerX)
    val centerYAnimator = ObjectAnimator.ofFloat(view, "y", view.y, centerY)

    centerXAnimator.interpolator = elasticInterpolator
    centerYAnimator.interpolator = elasticInterpolator

    centerXAnimator.duration = 500
    centerYAnimator.duration = 500

    centerXAnimator.start()
    centerYAnimator.start()

    centerXAnimator.addListener(object : AnimatorListenerAdapter() {
      override fun onAnimationEnd(animator: Animator) {
        val widthAnimator = ObjectAnimator.ofInt(view, "width", view.width, parent.width)
        val heightAnimator = ObjectAnimator.ofInt(view, "height", view.height, parent.height)

        widthAnimator.interpolator = elasticInterpolator
        heightAnimator.interpolator = elasticInterpolator

        widthAnimator.duration = 500
        heightAnimator.duration = 500

        widthAnimator.start()
        heightAnimator.start()
      }
    })
  }

  fun onDown(view: View, event: MotionEvent) {
    state.dX = view.x - event.rawX
    state.dY = view.y - event.rawY
    
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