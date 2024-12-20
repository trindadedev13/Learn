package dev.trindadedev.learn.ui.components.drag

data class DragLayoutState(
  var originalY: Float = 0f,
  var originalX: Float = 0f,
  var onUp: OnUp = OnUp(),
  var onMove: OnMove = OnMove()
) {
  data class OnUp(
    var heightAnimDuration: Long = 500,
    var widthAnimDuration: Long = 500,
    var goBackXAnimDuration: Long = 500,
    var goBackYAnimDuration: Long = 500,
  )
  data class OnMove(
    var dY: Float = 0f,
    var dX: Float = 0f,
    var animDuration: Long = 0
  )
}