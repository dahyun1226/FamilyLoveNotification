package com.example.familylovenotification.base.ext

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

fun Application.registerActivityLifecycleCallback() =
    registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
            Log.e("Activity lifecycle log", activity.localClassName + " ActivityCreated")
        }

        override fun onActivityStarted(activity: Activity) {
            Log.e("Activity lifecycle log", activity.localClassName + " ActivityStarted")
        }

        override fun onActivityResumed(activity: Activity) {
            Log.e("Activity lifecycle log", activity.localClassName + " ActivityResumed")
        }

        override fun onActivityPaused(activity: Activity) {
            Log.e("Activity lifecycle log", activity.localClassName + " ActivityPaused")
        }

        override fun onActivityStopped(activity: Activity) {
            Log.e("Activity lifecycle log", activity.localClassName + " ActivityStopped")
        }

        override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
            Log.e("Activity lifecycle log", activity.localClassName + " ActivitySaveInstanceState")
        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.e("Activity lifecycle log", activity.localClassName + " ActivityDestroyed")
        }
    })
