package com.example.ucp2.repository

import com.example.ucp2.data.dao.DosenDao
import com.example.ucp2.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface RepositoryDosen

//aturan untuk mengelola data dosen
{
    suspend fun insertDosen(dosen: Dosen)

    fun getAllDosen(): Flow<List<Dosen>>

    fun getDosen(nidn: String) : Flow<Dosen>


}