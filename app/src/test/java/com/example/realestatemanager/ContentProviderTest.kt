package com.example.realestatemanager

import android.database.Cursor
import android.net.Uri
import com.example.realestatemanager.data.local.contentProvider.ContentProvider
import com.example.realestatemanager.data.local.property.PropertyDao
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertSame
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ContentProviderTest {
    /** TEST CONTENT PROVIDER */

    @Mock
    lateinit var propertyDao: PropertyDao

    private lateinit var contentProvider: ContentProvider

    @Before
    fun setup() {
        contentProvider = ContentProvider().apply {
            this.propertyDao = this@ContentProviderTest.propertyDao // Injection manuelle dans le test
        }
    }

    @Test
    fun testQueryProperties() {
        // Simulate Cursor for property
        val mockedCursor: Cursor = mock(Cursor::class.java)

        // Simulate behavior from DAO for retrieve the mockedCursor
        Mockito.`when`(propertyDao.getAllPropertiesAsCursor()).thenReturn(mockedCursor)
        Mockito.`when`(mockedCursor.moveToFirst()).thenReturn(true)
        Mockito.`when`(mockedCursor.moveToNext()).thenReturn(true, false)
        Mockito.`when`(mockedCursor.getString(Mockito.eq(0))).thenReturn("Property 1")
        Mockito.`when`(mockedCursor.getInt(Mockito.eq(1))).thenReturn(100000)

        // Call query method from ContentProvider
        val uri = mock(Uri::class.java)
        val cursor = contentProvider.query(uri, null, null, null, null)

        // Check if Dao is called and if the result is right
        verify(propertyDao).getAllPropertiesAsCursor()
        assertNotNull(cursor) // Check if Cursor isn't null
        assertSame(mockedCursor, cursor) // Check if the Cursor is same
        assertNotNull(cursor)
        assertTrue(cursor!!.moveToFirst())
        assertEquals("Property 1", cursor.getString(0))
        assertEquals(100000, cursor.getInt(1))
    }
}