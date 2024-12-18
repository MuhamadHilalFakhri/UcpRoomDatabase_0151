package com.example.ucp2.repository

import com.example.ucp2.data.dao.MataKuliahDao
import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMK
    (private val mataKuliahDao: MataKuliahDao) : RepositoryMK
{
    override suspend fun insertMk(mataKuliah: MataKuliah) {
        mataKuliahDao.insertMatkul(mataKuliah)
    }

    override fun getAllMk(): Flow<List<MataKuliah>> {
        return mataKuliahDao.getAllMatkul()
    }

    override fun getMk(kode: String): Flow<MataKuliah> {
        return mataKuliahDao.getMatkul(kode)
    }

    override suspend fun deleteMk(mataKuliah: MataKuliah) {
        mataKuliahDao.deleteMatkul(mataKuliah)
    }

    override suspend fun updateMk(mataKuliah: MataKuliah) {
        mataKuliahDao.updateMatkul(mataKuliah)
    }
}