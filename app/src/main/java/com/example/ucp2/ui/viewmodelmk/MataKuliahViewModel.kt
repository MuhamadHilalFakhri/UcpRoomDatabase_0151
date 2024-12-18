package com.example.ucp2.ui.viewmodelmk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMK
import kotlinx.coroutines.launch

class MataKuliahViewModel(private val repositoryMK: RepositoryMK) : ViewModel()
{
    var uiStateMK by mutableStateOf(MatkulUIState())

    // Memperbarui state berdasarkan input pengguna
    fun updateStateMK(mataKuliahEvent: MataKuliahEvent) {
        uiStateMK = uiStateMK.copy(
            mataKuliahEvent = mataKuliahEvent
        )
    }

    // Validasi data input pengguna
    private fun validateFieldsMK(): Boolean {
        val event = uiStateMK.mataKuliahEvent
        val errorState = FormErrorState(
            kode = if (event.kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong"
        )

        uiStateMK = uiStateMK.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    // Menyimpan data ke repository
    fun saveDataMK() {
        val currentEvent = uiStateMK.mataKuliahEvent
        if (validateFieldsMK()) {
            viewModelScope.launch {
                try {
                    repositoryMK.insertMk(currentEvent.toMataKuliahEntity())
                    uiStateMK = uiStateMK.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        mataKuliahEvent = MataKuliahEvent(), // Reset input form
                        isEntryValid = FormErrorState() // Reset error state
                    )
                } catch (e: Exception) {
                    uiStateMK = uiStateMK.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiStateMK = uiStateMK.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda."
            )
        }
    }


    // Reset pesan Snackbar setelah ditampilkan
    fun resetSnackBarMessageMK() {
        uiStateMK = uiStateMK.copy(
            snackBarMessage = null
        )
    }

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
