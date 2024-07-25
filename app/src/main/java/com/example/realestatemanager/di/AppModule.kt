package com.example.realestatemanager.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.realestatemanager.database.datasource.Property
import com.example.realestatemanager.database.datasource.PropertyDao
import com.example.realestatemanager.database.datasource.PropertyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private var INSTANCE: PropertyDatabase? = null

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): PropertyDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            PropertyDatabase::class.java,
            "Property.db"
        ).addCallback(object : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val propertyDao = INSTANCE?.propertyDao()
                propertyDao?.insertFake(
                    Property(
                        id = 0,
                        type = "Lounge",
                        price = 41001100,
                        surface = 140,
                        room = 4,
                        image = "",
                        description = "A wonderful house type of Lounge with a good view on the city",
                        address = "14 Garp Street",
                        interest = "",
                        status = "For sell",
                        dateOfCreation = 12022024,
                        dateOfSold = 14052024,
                        agent = "Axel",
                    )
                )
                propertyDao?.insertFake(
                    Property(
                        id = 0,
                        type = "House",
                        price = 45601200,
                        surface = 160,
                        room = 5,
                        image = "",
                        description = "A wonderful house with a good view on the city. possibility of changing the attic into a bedroom",
                        address = "140 Park Avenue",
                        interest = "",
                        status = "Sold",
                        dateOfCreation = 12022024,
                        dateOfSold = 14052024,
                        agent = "Axel",
                    )
                )
            }
        }).build()
    }

    @Provides
    fun provideTaskDao(database: PropertyDatabase): PropertyDao = database.propertyDao()
}