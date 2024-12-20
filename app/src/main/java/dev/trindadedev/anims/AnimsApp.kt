package dev.trindadedev.anims

import android.app.Application
import com.google.android.material.color.DynamicColors

class AnimsApp: Application() {
  override fun onCreate() {
    DynamicClolors.applyToActivitiesIfAvailable(this)
    super.onCreate()
  }
}