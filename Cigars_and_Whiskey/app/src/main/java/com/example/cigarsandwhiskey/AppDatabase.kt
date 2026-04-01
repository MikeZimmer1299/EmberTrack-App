package com.example.cigarsandwhiskey

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cigarsandwhiskey.dataAccessObjects.CigarReviewDao
import com.example.cigarsandwhiskey.dataAccessObjects.MyCigarsDao
import com.example.cigarsandwhiskey.dataAccessObjects.MyWhiskeyDao
import com.example.cigarsandwhiskey.dataAccessObjects.WhiskeyReviewDao
import com.example.cigarsandwhiskey.objects.CigarReviews
import com.example.cigarsandwhiskey.objects.MyCigars
import com.example.cigarsandwhiskey.objects.MyHumidor
import com.example.cigarsandwhiskey.objects.MyWhiskey
import com.example.cigarsandwhiskey.objects.WhiskeyReviews


@Database(
    entities = [CigarReviews::class, WhiskeyReviews::class, MyCigars::class, MyWhiskey::class, MyHumidor::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun myCigars(): MyCigarsDao
    abstract fun cigarReviewDao(): CigarReviewDao
    abstract fun myWhiskeyDao(): MyWhiskeyDao
    abstract fun myWhiskeyReviewDao(): WhiskeyReviewDao
    abstract fun myHumidorsDao(): MyHumidor


    companion object{
        @Volatile // ensures value of INSTANCE is always read from main memory
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){ // if INSTANCE true, return; else synchronize via one thread at a time
                val instance = Room.databaseBuilder( // prevents being tied to specific activity's lifecycle
                    context.applicationContext,
                    AppDatabase::class.java, // calls the blueprint for database
                    "cigars_and_whiskey_database" // filename of SQLite database
                )
                    .build()
                INSTANCE = instance // caching database
                instance // result database, returns with `return INSTANCE` after being built
            }
        }
    }

}