package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.DosenDao
import com.example.ucp2.data.dao.MataKuliahDao
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.MataKuliah

@Database(entities = [Dosen::class, MataKuliah::class], version = 1, exportSchema = false)
abstract class AllDatabase : RoomDatabase()
{

    // Mendefinisikan fungsi untuk mengakses DAO
    abstract fun dosenDao(): DosenDao
    abstract fun mataKuliahDao(): MataKuliahDao

    companion object {
        @Volatile
        private var INSTANCE: AllDatabase? = null

        fun getDatabase(context: Context): AllDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AllDatabase::class.java, // Class database
                    "AllDatabase" // Nama database
                ).build().also { INSTANCE = it }
            }
        }
    }
}