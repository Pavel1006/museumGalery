import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.metmuseum.models.ArtDetails
import com.example.metmuseum.ui.details.DetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    onUpClick: () -> Unit,
    viewModel: DetailsViewModel = koinViewModel(),

    ) {
    val details by viewModel.details.collectAsStateWithLifecycle()


    DetailsScreen(
        details = details,
        onUpClick = onUpClick

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsScreen(
    details: ArtDetails,
    onUpClick: () -> Unit,

    ) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onUpClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = details.objectID.toString())


                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (details.primaryImage.isNotBlank()) {
                AsyncImage(
                    model = details.primaryImage,
                    contentDescription = "Artwork Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            if (details.additionalImages.size < 3) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    details.additionalImages.forEach { imageUrl ->
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Additional Image",
                            modifier = Modifier
                                .size(150.dp)
                                .padding(horizontal = 4.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            } else {
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(details.additionalImages) { imageUrl ->
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Additional Image",
                            modifier = Modifier
                                .size(150.dp)
                                .padding(end = 8.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            Column (
                modifier = Modifier.verticalScroll(rememberScrollState())
            ){
                val detailsTextColor = Color(0xFF722F37)
                Text(
                    text = "Object Name: ${details.objectName}",
                    fontSize = 22.sp,
                    color = detailsTextColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Title: ${details.title}",
                    fontSize = 22.sp,
                    color = detailsTextColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(text = "Period: ${details.period}", fontSize = 22.sp)
                Text(text = "Culture: ${details.culture}", fontSize = 22.sp)
                Text(text = "Accession Year: ${details.accessionYear}", fontSize = 22.sp)
                Text(text = "Department: ${details.department}", fontSize = 22.sp)
                Text(text = "Accession Number: ${details.accessionNumber}", fontSize = 22.sp)
                Text(text = "Is Public Domain: ${details.isPublicDomain}", fontSize = 22.sp)
                Text(text = "Is Highlight: ${details.isHighlight}", fontSize = 22.sp)
                Text(text = "Dynasty: ${details.dynasty}", fontSize = 22.sp)
                Text(text = "Reign: ${details.reign}", fontSize = 22.sp)
                Text(text = "Portfolio: ${details.portfolio}", fontSize = 22.sp)
                Text(text = "Artist Role: ${details.artistRole}", fontSize = 22.sp)
                Text(text = "Artist Prefix: ${details.artistPrefix}", fontSize = 22.sp)
                Text(text = "Artist Display Name: ${details.artistDisplayName}", fontSize = 22.sp)
                Text(text = "Artist Display Bio: ${details.artistDisplayBio}", fontSize = 22.sp)
                Text(text = "Artist Suffix: ${details.artistSuffix}", fontSize = 22.sp)
                Text(text = "Artist Alpha Sort: ${details.artistAlphaSort}", fontSize = 22.sp)
                Text(text = "Artist Nationality: ${details.artistNationality}", fontSize = 22.sp)
                Text(text = "Artist Begin Date: ${details.artistBeginDate}", fontSize = 22.sp)
                Text(text = "Artist End Date: ${details.artistEndDate}", fontSize = 22.sp)
                Text(text = "Artist Gender: ${details.artistGender}", fontSize = 22.sp)
            }
        }
    }
}