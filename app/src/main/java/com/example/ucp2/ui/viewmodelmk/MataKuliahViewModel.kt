package com.example.ucp2.ui.viewmodelmk

import androidx.lifecycle.ViewModel
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMK

class MataKuliahViewModel(private val repositoryMK: RepositoryMK) : ViewModel()
{

    data class MatkulUIState(
        val mataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
        val isEntryValid: FormErrorState = FormErrorState(),
        val snackBarMessage: String? = null,
    )

    data class FormErrorState(
        val kode: String? = null,
        val nama: String? = null,
        val sks: String? = null,
        val semester: String? = null,
        val dosenPengampu: String? = null,
    ) {
        fun isValid(): Boolean {
            return kode == null && nama == null && sks == null &&
                    semester == null && dosenPengampu == null
        }
    }

    data class MataKuliahEvent(
        val kode: String = "",
        val nama: String = "",
        val sks: String = "",
        val semester: String = "",
        val dosenPengampu: String = ""
    )

    // Menyimpan input form ke dalam entity
    fun MataKuliahEvent.toMataKuliahEntity(): MataKuliah = MataKuliah(
        kode = kode,
        nama = nama,
        sks = sks,
        semester = semester,
        dosenPengampu = dosenPengampu
    )
}
