package dev.trindadedev.learn.ui.components.bounce

import android.animation.ValueAnimator
import android.view.animation.DecelerateInterpolator
import android.view.MotionEvent
import android.view.View
import dev.trindadedev.learn.ui.components.Events

class BounceLayoutEvents(
  private val state: ElasticMoveableLayoutState = ElasticMoveableLayoutState(),
): Event {

  override fun onUp(view: View, event: MotionEvent) {
    val target = if (state.currentBound > (state.targetBound - state.initialBound) / 2) state.targetBound else state.initialBound
    animateToBound(view, target)
  }

  override fun onDown(view: View, event: MotionEvent) {
    // do nothing
  }

  override fun onMove(view: View, event: MotionEvent) {
    val touchY = event.rawY
    state.currentBound = touchY.coerceIn(state.initialBound, state.targetBound)
    view.layoutParams.height = state.currentBound.toInt()
    view.requestLayout()
  }
  
  private fun animateToBound(view: View, target: Float) {
    val start = state.currentBound
    val animator = ValueAnimator.ofFloat(start, target)
    animator.duration = 300L
    animator.interpolator = DecelerateInterpolator()
    animator.addUpdateListener { animation ->
      val value = animation.animatedValue as Float
      view.layoutParams.height = value.toInt()
      view.requestLayout()
      state.currentBound = value
    }
    animator.start()
  }
}