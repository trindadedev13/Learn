package dev.trindadedev.learn.ui.components.drag

data class DragLayoutState(
  var originalY: Float = 0f,
  var originalX: Float = 0f,
  var onUp: OnUp = OnUp(),
  var onMove: OnMove = OnMove()
) {
  data class OnUp(
    var heightAnimDuration: Int = 0,
    var widthAnimDuration: Int = 0,
    var goBackXAnimDuration: Int = 0,
    var goBackYAnimDuration: Int = 0,
  )
  data class OnMove(
    var dY: Float = 0f,
    var dX: Float = 0f,
    var animDuration: Long = 0
  )
}