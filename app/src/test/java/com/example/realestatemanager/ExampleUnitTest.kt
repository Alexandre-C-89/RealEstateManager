package com.example.realestatemanager

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.example.realestatemanager.util.NetworkUtil
import com.example.realestatemanager.util.Utils
import junit.framework.Assert.assertTrue
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    val EURO_TO_DOLLAR_RATE = 1.1
    val DOLLAR_TO_EURO_RATE = 1 / EURO_TO_DOLLAR_RATE

    private fun convertEuroToDollar(euro: Double): Double {
        return euro * EURO_TO_DOLLAR_RATE
    }

    private fun convertDollarToEuro(dollar: Double): Double {
        return dollar * DOLLAR_TO_EURO_RATE
    }

    fun getTodayDate(): LocalDate {
        return LocalDate.now()
    }

    @Test
    fun testConvertEuroToDollar() {
        val euros = 100.0
        val expectedDollars = euros * EURO_TO_DOLLAR_RATE
        assertEquals(expectedDollars, convertEuroToDollar(euros), 0.001)
    }

    @Test
    fun testConvertDollarToEuro() {
        val dollars = 100
        val expectedEuros = (dollars * 0.812).roundToInt()
        assertEquals(expectedEuros, Utils.convertDollarToEuro(dollars))
    }

    @Test
    fun testGetTodayDate() {
        // keep date
        val today = LocalDate.now()
        val result = getTodayDate()
        assertEquals(today, result)

        val formattedDate = result.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        val datePattern = Regex("""\d{2}-\d{2}-\d{4}""")
        assertTrue(result.toString().matches(datePattern).toString(), true)
    }

    @Test
    fun testIsNetworkAvailable() {
        // Mock Context et ConnectivityManager
        val context: Context = mock(Context::class.java)
        val connectivityManager: ConnectivityManager = mock(ConnectivityManager::class.java)
        val network: Network = mock(Network::class.java)
        val networkCapabilities: NetworkCapabilities = mock(NetworkCapabilities::class.java)

        // Simuler le comportement pour une connexion Internet simple
        Mockito.`when`(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
        Mockito.`when`(connectivityManager.activeNetwork).thenReturn(network)
        Mockito.`when`(connectivityManager.getNetworkCapabilities(network)).thenReturn(networkCapabilities)

        // Simuler que le réseau a la capacité d'accéder à Internet
        Mockito.`when`(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)).thenReturn(true)

        // Vérifier le résultat
        val result = NetworkUtil.isNetworkAvailable(context)
        assertTrue(result)
    }


    @Test
    fun testNoNetworkAvailable() {
        // Mock Context et ConnectivityManager
        val context: Context = mock(Context::class.java)
        val connectivityManager: ConnectivityManager = mock(ConnectivityManager::class.java)
        val network: Network = mock(Network::class.java)
        val networkCapabilities: NetworkCapabilities = mock(NetworkCapabilities::class.java)

        // Simuler le comportement pour une absence de connexion
        Mockito.`when`(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
        Mockito.`when`(connectivityManager.activeNetwork).thenReturn(null)

        // Vérifier le résultat
        val result = NetworkUtil.isNetworkAvailable(context)
        assertFalse(result)
    }

}