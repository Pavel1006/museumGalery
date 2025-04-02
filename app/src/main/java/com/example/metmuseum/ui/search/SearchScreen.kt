package com.example.metmuseum.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    onResultClick: (Int) -> Unit,
    onFavTextClick: () -> Unit,
    viewModel: SearchViewModel = koinViewModel(),
){
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()

    SearchScreenPrivate(
        searchResults = searchResults,
        onResultClick = onResultClick,
        searchText = searchText,
        onSearchTextChange = viewModel::onSearchTextChange,

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchScreenPrivate(
    searchResults: Array<Int>,
    onResultClick: (Int) -> Unit,
    searchText: String,
    onSearchTextChange: (String) -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)  // Add some padding inside the TextField
                .clip(RoundedCornerShape(50.dp))  // Use a higher value for more oval corners
                .background(Color(0xFFEFB8C8)), // Lavender background (using Hex color code for lavender)
            placeholder = { Text(text = "Search", color = Color.White) },
            textStyle = TextStyle(color = Color.White),  // Set text color to white
            shape = RoundedCornerShape(50.dp),  // Apply oval shape (rounded corners)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(searchResults.toList()) { id ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$id",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onResultClick(id) },
                    )

                }
            }
        }
    }
}

