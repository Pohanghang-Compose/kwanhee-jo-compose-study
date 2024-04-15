package com.koreatech.kwanhee_jo_compose_study

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PohahangApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}