package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.SisforApp

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
    }
}

fun CreationExtras.sisforApp(): SisforApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SisforApp)
