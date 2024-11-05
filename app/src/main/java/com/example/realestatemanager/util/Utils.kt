package com.example.realestatemanager.util

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.math.roundToInt

object Utils {

    @RequiresApi(Build.VERSION_CODES.O)
    private val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

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
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatCustomDate(dateLong: Long?): String {
        if (dateLong == null) return "Date not specified"
        return try {
            val dateStr = dateLong.toString().padStart(8, '0')
            val parsedDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("ddMMyyyy"))
            parsedDate.format(dateFormatter)
        } catch (e: DateTimeParseException){
            "Invalid date format"
        }
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