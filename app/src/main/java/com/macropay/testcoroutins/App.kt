package com.macropay.testcoroutins

import android.app.Application
import com.macropay.testcoroutins.flow.ItemsProvider

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        ItemsProvider.startEmitting()
    }
}