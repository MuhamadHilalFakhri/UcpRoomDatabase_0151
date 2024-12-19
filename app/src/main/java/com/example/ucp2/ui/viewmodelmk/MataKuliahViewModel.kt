package com.example.ucp2.ui.viewmodelmk

import androidx.lifecycle.ViewModel
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMK

class MataKuliahViewModel(private val repositoryMK: RepositoryMK) : ViewModel()
{

    // Menyimpan input form ke dalam entity
    fun MataKuliahEvent.toMataKuliahEntity(): MataKuliah = MataKuliah(
        kode = kode,
        nama = nama,
        sks = sks,
        semester = semester,
        dosenPengampu = dosenPengampu
    )
}
