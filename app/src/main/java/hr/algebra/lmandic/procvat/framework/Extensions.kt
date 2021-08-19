package hr.algebra.lmandic.procvat.framework

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.animation.AnimationUtils


fun View.applyAnimation(resourceId: Int)
    = startAnimation(AnimationUtils.loadAnimation(context, resourceId))

inline fun<reified T: Activity> Context.startActivity() = startActivity(Intent(this, T::class.java))

inline fun<reified T: Activity> Context.startActivity(key: String, value: Int)
        = startActivity(Intent(this, T::class.java).apply { putExtra(key, value) })