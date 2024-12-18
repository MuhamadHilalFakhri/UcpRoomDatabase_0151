package com.example.ucp2.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

interface MataKuliahDao {
    @Insert
    suspend fun insertMatkul(mataKuliah: MataKuliah)

    @Query("SELECT * FROM mataKuliah ORDER BY nama ASC")
    fun getAllMatkul(): Flow<List<MataKuliah>>

    @Query("SELECT * FROM mataKuliah WHERE kode = :kode ")
    fun  getMatkul(kode: String): Flow<MataKuliah>

    @Delete
    suspend fun deleteMatkul(mataKuliah: MataKuliah)

    @Update
    suspend fun updateMatkul(mataKuliah: MataKuliah)
}