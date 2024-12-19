package com.example.ucp2.ui.view.dosen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.ui.customwidget.CustomTopAppBar
import com.example.ucp2.ui.viewmodeldosen.DetailDosenViewModel
import com.example.ucp2.ui.viewmodeldosen.DetailUiState
import com.example.ucp2.ui.viewmodeldosen.PenyediaViewModel
import com.example.ucp2.ui.viewmodeldosen.toDosenEntity

@Composable
fun DetailDosenView(
    modifier: Modifier = Modifier,
    viewModel: DetailDosenViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack: () -> Unit = { },
    onCreateClick: () -> Unit = {},
){
    Scaffold (
        topBar = {
            CustomTopAppBar(
                judul = "Detail Dosen",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onCreateClick()
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Dosen"
                )
            }
        }
    ) { innerPadding ->
        val detailUiState by viewModel.detailUiState.collectAsState()
        BodyDetailDosen(
            modifier = Modifier.padding(innerPadding),
            detailUiState = detailUiState
        )
    }
}

@Composable
fun BodyDetailDosen(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState = DetailUiState(),
){
    when{
        detailUiState.isLoading ->{
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }

        detailUiState.isUiEventNotEmpty ->{
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailDosen(
                    dosen = detailUiState.detailUiEvent.toDosenEntity(),
                    modifier = Modifier
                )
            }
        }

        detailUiState.isUiEventNotEmpty ->{
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


@Composable
fun ItemDetailDosen(
    modifier: Modifier = Modifier,
    dosen: Dosen
){
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailDosen(judul = "NIDN", isinya = dosen.nidn)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailDosen(judul = "Nama", isinya = dosen.nama)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailDosen(judul = "Jenis Kelamin", isinya = dosen.jenisKelamin)
        }
    }
}

@Composable
fun ComponentDetailDosen(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Text(
            text = isinya, fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
