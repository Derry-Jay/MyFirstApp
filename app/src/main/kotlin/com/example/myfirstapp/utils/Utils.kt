package com.example.myfirstapp.utils

import android.view.View

fun getDeviceService(vw: View, name: String): Any {
    return vw.context.getSystemService(name)
}