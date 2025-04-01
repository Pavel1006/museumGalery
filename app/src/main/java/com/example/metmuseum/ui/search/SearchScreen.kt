package com.example.metmuseum.ui.search

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = viewModel()
) {
    var query by remember { mutableStateOf("") }

    val artObjects by searchViewModel.artObjects.collectAsState()
    val isLoading by searchViewModel.isLoading.collectAsState()
    val error by searchViewModel.error.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Căutare obiecte de artă") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { searchViewModel.searchArtObjects(query) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Căutare")
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        if (error != null) {
            Text(text = error ?: "", color = MaterialTheme.colors.error)
        }

        LazyColumn {
            items(artObjects) { artObject ->
                ArtObjectItem(artObject = artObject)
            }
        }
    }
}

@Composable
fun ArtObjectItem(artObject: ArtObjectApi) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = artObject.title, style = MaterialTheme.typography.h6)

        // Dacă există o imagine
        if (!artObject.imageUrl.isNullOrEmpty()) {
            Image(
                painter = rememberImagePainter(artObject.imageUrl),
                contentDescription = artObject.title,
                modifier = Modifier.fillMaxWidth().height(200.dp),
                contentScale = ContentScale.Crop
            )
        }

        Text(text = artObject.artist ?: "Necunoscut", color = Color.Gray)
        Text(text = artObject.date ?: "Data necunoscută", color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = artObject.description ?: "Descriere necunoscută", color = Color.Gray)
    }
}

@Preview
@Composable
fun PreviewSearchScreen() {
    SearchScreen()
}
