package com.example.realestatemanager.util

import android.net.Uri

interface CameraManager {
    suspend fun takePhoto(): Uri?
}