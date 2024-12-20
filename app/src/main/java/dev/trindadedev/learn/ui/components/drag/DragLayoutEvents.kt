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

class DragLayoutEvents(
  private val context: Context,
  private val state: DragLayoutState = DragLayoutState()
) {

  fun onUp(view: View, event: MotionEvent) {
    val parent = view.parent as ViewGroup
    val centerX = (parent.width - view.width) / 2f
    val centerY = (parent.height - view.height) / 2f

    val elasticInterpolator = OvershootInterpolator(1.5f)

    val goBackXAnimator = ObjectAnimator.ofFloat(view, "x", view.x, state.originalX)
    val goBackYAnimator = ObjectAnimator.ofFloat(view, "y", view.y, state.originalY)

    goBackXAnimator.interpolator = elasticInterpolator
    goBackYAnimator.interpolator = elasticInterpolator

    goBackXAnimator.duration = state.onUp.goBackXAnimDuration
    goBackYAnimator.duration = state.onUp.goBackYAnimDuration

    goBackXAnimator.start()
    goBackYAnimator.start()

    goBackXAnimator.addListener(object : AnimatorListenerAdapter() {
      override fun onAnimationEnd(animator: Animator) {
        val widthAnimator = ObjectAnimator.ofInt(view, "width", view.width, parent.width)
        val heightAnimator = ObjectAnimator.ofInt(view, "height", view.height, parent.height)

        widthAnimator.interpolator = elasticInterpolator
        heightAnimator.interpolator = elasticInterpolator

        widthAnimator.duration = state.onUp.widthAnimDuration
        heightAnimator.duration = state.onUp.heightAnimDuration

        widthAnimator.start()
        heightAnimator.start()
      }
    })
  }

  fun onDown(view: View, event: MotionEvent) {
    state.originalY = view.y
    state.originalX = view.x
    state.onMove.dX = view.x - event.rawX
    state.onMove.dY = view.y - event.rawY
  }

  fun onMove(view: View, event: MotionEvent) {
    val parent = view.parent as ViewGroup
    val newX = (event.rawX + state.onMove.dX).coerceIn(0f, parent.width - view.width.toFloat())
    val newY = (event.rawY + state.onMove.dY).coerceIn(0f, parent.height - view.height.toFloat())

    view.animate()
      .x(newX)
      .y(newY)
      .setDuration(state.onMove.animDuration)
      .start()
  }
}