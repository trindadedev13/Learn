package dev.trindadedev.learn.utils

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator

internal fun Context.vibrate(duration: Long) {
  val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
  if (vibrator.hasVibrator()) {
    val effect = VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)
    vibrator.vibrate(effect)
  }
}
