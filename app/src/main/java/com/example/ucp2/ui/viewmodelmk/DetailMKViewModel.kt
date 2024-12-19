package com.example.ucp2.ui.viewmodelmk

import com.example.ucp2.data.entity.MataKuliah


// Memindahkan data dari entity ke UI
fun MataKuliah.toDetailUiEvent(): MataKuliahEvent {
    return MataKuliahEvent(
        kode = kode,
        nama = nama,
        sks = sks,
        semester = semester,
        dosenPengampu = dosenPengampu
    )
}

fun MataKuliahEvent.toMataKuliahEntity(): MataKuliah = MataKuliah(
    kode = kode,
    nama = nama,
    sks = sks,
    semester = semester,
    dosenPengampu = dosenPengampu
)
data class MataKuliahEvent(
    val kode: String = "",
    val nama: String = "",
    val sks: String = "",
    val semester: String = "",
    val dosenPengampu: String = ""
)