package com.example.realestatemanager

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

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

    fun convertEuroToDollar(euro: Double): Double {
        return euro * EURO_TO_DOLLAR_RATE
    }

    fun convertDollarToEuro(dollar: Double): Double {
        return dollar * DOLLAR_TO_EURO_RATE
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

}