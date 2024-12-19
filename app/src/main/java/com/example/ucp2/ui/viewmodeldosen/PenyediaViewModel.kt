package com.example.ucp2.ui.viewmodeldosen

import UpdateMatkulViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.SisforApp
import com.example.ucp2.ui.viewmodelmk.DetailMataKuliahViewModel
import com.example.ucp2.ui.viewmodelmk.HomeMatkulViewModel
import com.example.ucp2.ui.viewmodelmk.MataKuliahViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                sisforApp().containerApp.repositoryDosen
            )
        }
        initializer {
            HomeDosenViewModel(
                sisforApp().containerApp.repositoryDosen
            )
        }
        initializer {
            DetailDosenViewModel(
                createSavedStateHandle(),
                sisforApp().containerApp.repositoryDosen
            )
        }
        initializer {
            MataKuliahViewModel(
                sisforApp().containerApp.repositoryMK
            )
        }
        initializer {
            HomeMatkulViewModel(
                sisforApp().containerApp.repositoryMK
            )
        }
        initializer {
            DetailMataKuliahViewModel(
                createSavedStateHandle(),
                sisforApp().containerApp.repositoryMK
            )
        }
        initializer {
            UpdateMatkulViewModel(
                createSavedStateHandle(),
                sisforApp().containerApp.repositoryMK,

                )
        }
    }
}

fun CreationExtras.sisforApp(): SisforApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SisforApp)
