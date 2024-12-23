package com.example.ucp2.ui.view.dosen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.R
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.ui.customwidget.CustomTopAppBar
import com.example.ucp2.ui.viewmodeldosen.HomeDosenViewModel
import com.example.ucp2.ui.viewmodeldosen.HomeUiState
import com.example.ucp2.ui.viewmodeldosen.PenyediaViewModel
import kotlinx.coroutines.launch
@Composable
fun HomeDosenView(
    viewModel: HomeDosenViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddDosen: () -> Unit = { }, // Fungsi untuk menambahkan dosen baru
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddDosen, // Panggil fungsi untuk menambahkan dosen baru
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp),
                containerColor = colorResource(id = R.color.button)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = colorResource(id = R.color.white),
                    contentDescription = "Tambah Dosen"
                )
            }
        }
    ) { innerPadding ->
        val dosenUiState by viewModel.homeUiState.collectAsState()
        BodyHomeDosenView(
            dosenUiState = dosenUiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeDosenView(
    dosenUiState: HomeUiState,
    modifier: Modifier = Modifier

) {
    Column(modifier = modifier.fillMaxSize()
        .background(
            color = colorResource(
                id = R.color.primary
            )
        )) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(110.dp),
                painter = painterResource(id = R.drawable.umy),
                contentDescription = "Logo UMY"
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(
                    text = "Universitas Muhammadiyah Yogyakarta",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Teknologi Informasi",
                    color = Color.White,
                    fontWeight = FontWeight.Light
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(
                        id = R.color.bg
                    ),
                    shape = RoundedCornerShape(
                        topEnd = 15.dp,
                        topStart = 15.dp
                    )
                )
        ){
            when {
                dosenUiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                dosenUiState.isError -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Terjadi kesalahan.",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                dosenUiState.listDosen.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Tidak ada data dosen.",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                else -> {
                    ListDosen(
                        listDosen = dosenUiState.listDosen,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}


@Composable
fun ListDosen(
    listDosen: List<Dosen>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = listDosen,
            itemContent = { dosen ->
                ExpandableCardDosen(dosen = dosen)
            }
        )
    }
}

@Composable
fun ExpandableCardDosen(
    dosen: Dosen,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    val cardBackgroundColor = colorResource(id = R.color.primary)

    Card(
        onClick = { isExpanded = !isExpanded },
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = cardColors(
            containerColor = cardBackgroundColor
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = dosen.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f),
                    color = Color.White
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.ArrowDropDown,
                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "NIDN: ${dosen.nidn}",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Jenis Kelamin: ${dosen.jenisKelamin}",
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
