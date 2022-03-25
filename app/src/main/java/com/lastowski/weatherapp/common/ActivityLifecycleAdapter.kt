package com.lastowski.weatherapp.common

import android.app.Activity
import android.app.Application
import android.os.Bundle

open class ActivityLifecycleAdapter : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        //no-op
    }

    override fun onActivityStarted(activity: Activity) {
        //no-op
    }

    override fun onActivityResumed(activity: Activity) {
        //no-op
    }

    override fun onActivityPaused(activity: Activity) {
        //no-op
    }

    override fun onActivityStopped(activity: Activity) {
        //no-op
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
        //no-op
    }

    override fun onActivityDestroyed(activity: Activity) {
        //no-op
    }
}