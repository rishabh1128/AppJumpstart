package com.phoenix.appjumpstart

import android.app.Application
import com.phoenix.appjumpstart.data.database.AppContainer

class AppJumpstartApplication : Application(){
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}