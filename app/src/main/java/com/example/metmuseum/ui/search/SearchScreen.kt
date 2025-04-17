package com.example.metmuseum.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    onResultClick: (Int) -> Unit,
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
            .padding(WindowInsets.systemBars.asPaddingValues()),
        verticalArrangement = if (searchResults.isEmpty() && searchText.isEmpty()) Arrangement.Center else Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (searchText.isEmpty()) {
            Text(
                text = "Welcome to MET Gallery Public API",
                fontSize = 30.sp,
                lineHeight = 40.sp,
                color = Color(0xFF2C81AF),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }

        TextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xFFEFB8C8)),
            placeholder = { Text(text = "Search", color = Color.White) },
            textStyle = TextStyle(color = Color.White)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(searchResults.toList()) { id ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .clip(RoundedCornerShape(1.dp))
                        .background(Color(0xFF464575))
                        .padding(vertical = 12.dp, horizontal = 16.dp)
                        .clickable { onResultClick(id) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$id",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    val mockResults = arrayOf(101, 202, 303,32432432,432432,4324324,324432,342432,342432)
    val mockSearchText = "Art"

    SearchScreenPrivate(
        searchResults = mockResults,
        onResultClick = {},
        searchText = mockSearchText,
        onSearchTextChange = {}
    )
}