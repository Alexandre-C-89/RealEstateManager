package com.example.realestatemanager.util

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class CameraManagerImpl @Inject constructor(
    private val context: Context
) : CameraManager {

    override suspend fun takePhoto(): Uri? {
        return withContext(Dispatchers.IO) {
            val file = File(context.cacheDir, "${System.currentTimeMillis()}.jpg")
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
            uri
        }
    }

}