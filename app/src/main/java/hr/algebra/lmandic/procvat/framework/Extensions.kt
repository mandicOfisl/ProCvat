package hr.algebra.lmandic.procvat.framework

import android.view.View
import android.view.animation.AnimationUtils


fun View.applyAnimation(resourceId: Int)
    = startAnimation(AnimationUtils.loadAnimation(context, resourceId))

