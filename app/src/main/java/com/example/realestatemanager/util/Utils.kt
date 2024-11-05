package com.example.realestatemanager.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Utils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTodayDate(): String {
        val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return LocalDate.now().format(dateFormat)
    }

}