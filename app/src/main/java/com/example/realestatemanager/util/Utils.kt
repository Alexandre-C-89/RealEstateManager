package com.example.realestatemanager.util

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

object Utils {

    /**
     * Converting price of property (Dollars to Euros)
     */
    fun convertDollarToEuro(dollars: Int): Int {
        return (dollars * 0.812).roundToInt()
    }

    /**
     * Converting today's date to "dd/MM/yyyy" format
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTodayDate(): String {
        val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return LocalDate.now().format(dateFormat)
    }

    /**
     * Formatting a date stored in `Long` in the format "dd-MM-yyyy"
     */
    fun formatCustomDate(dateLong: Long?): String {
        return dateLong?.toString()?.padStart(8, '0')?.let { dateStr ->
            val day = dateStr.substring(0, 2)
            val month = dateStr.substring(2, 4)
            val year = dateStr.substring(4, 8)
            "$day-$month-$year"
        } ?: "Date not specified"
    }

    /**
     * Check internet connexion
     */
    fun isInternetAvailable(context: Context): Boolean {
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return wifiManager.isWifiEnabled
    }

}

/*public class Utils {

    /**
     * Conversion d'un prix d'un bien immobilier (Dollars vers Euros)
     * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
     *
     * @param dollars
     * @return
     */
    public static int convertDollarToEuro(int dollars) {
        return (int) Math.round(dollars * 0.812);
    }

    /**
     * Conversion de la date d'aujourd'hui en un format plus approprié
     * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
     *
     * @return
     */
    public static String getTodayDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(new Date());
    }

    /**
     * Vérification de la connexion réseau
     * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
     *
     * @param context
     * @return
     */
    public static Boolean isInternetAvailable(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifi.isWifiEnabled();
    }
}*/