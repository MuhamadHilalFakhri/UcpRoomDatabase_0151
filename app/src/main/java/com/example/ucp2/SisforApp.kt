package com.example.ucp2

import android.app.Application
import com.example.ucp2.dependeciesinjection.ContainerApp

class SisforApp : Application() {

    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
        //instance adalah object yg dibuat dari class
    }
}