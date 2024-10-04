package com.example.realestatemanager

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.realestatemanager", appContext.packageName)
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
    fun testGetTodayDate() {
        // keep date
        val today = LocalDate.now()

        // Call function for test
        val result = getTodayDate()

        // Vérifier que la date retournée par la fonction est bien la date du jour
        assertEquals(today, result)
    }

    @Test
    fun testConvertEuroToDollar() {
        val euros = 100.0
        val expectedDollars = euros * EURO_TO_DOLLAR_RATE
        assertEquals(expectedDollars, convertEuroToDollar(euros), 0.001)
    }

    @Test
    fun testConvertDollarToEuro() {
        val dollars = 100.0
        val expectedEuros = dollars * DOLLAR_TO_EURO_RATE
        assertEquals(expectedEuros, convertDollarToEuro(dollars), 0.001)
    }

    /*@Test
    fun testIsNetworkAvailable() {
        // Crée un mock du Context et du ConnectivityManager
        val context: Context = mock()
        val connectivityManager: ConnectivityManager = mock()
        val networkInfo: NetworkInfo = mock()

        // Définis le comportement attendu
        whenever(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
        whenever(connectivityManager.activeNetworkInfo).thenReturn(networkInfo)
        whenever(networkInfo.isConnected).thenReturn(true)

        // Vérifie que la méthode isNetworkAvailable() renvoie true
        val result = NetworkUtil.isNetworkAvailable(context)
        assert(result)
    }*/

}