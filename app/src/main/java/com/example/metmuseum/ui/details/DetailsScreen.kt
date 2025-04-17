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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val detailsTextColor = Color(0xFF637593) // Special color for Object Name & Title

                listOf(
                    "Object Name" to details.objectName,
                    "Title" to details.title
                ).filter { it.second.isNotBlank() && it.second != "null" }
                    .forEach { (label, value) ->
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                ) {
                                    append("$label: ")
                                }
                                withStyle(style = SpanStyle(fontSize = 16.sp)) {
                                    append(value)
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            color = detailsTextColor,
                            textAlign = TextAlign.Center,
                            lineHeight = 30.sp
                        )
                    }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    listOf(
                        "Period" to details.period,
                        "Culture" to details.culture,
                        "Accession Year" to details.accessionYear,
                        "Department" to details.department,
                        "Accession Number" to details.accessionNumber,
                        "Is Public Domain" to details.isPublicDomain.toString(),
                        "Is Highlight" to details.isHighlight.toString(),
                        "Dynasty" to details.dynasty,
                        "Reign" to details.reign,
                        "Portfolio" to details.portfolio,
                        "Artist Role" to details.artistRole,
                        "Artist Prefix" to details.artistPrefix,
                        "Artist Display Name" to details.artistDisplayName,
                        "Artist Display Bio" to details.artistDisplayBio,
                        "Artist Suffix" to details.artistSuffix,
                        "Artist Alpha Sort" to details.artistAlphaSort,
                        "Artist Nationality" to details.artistNationality,
                        "Artist Begin Date" to details.artistBeginDate,
                        "Artist End Date" to details.artistEndDate,
                        "Artist Gender" to details.artistGender
                    ).filter { it.second.isNotBlank() && it.second != "null" }
                        .forEach { (label, value) ->
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)) {
                                        append("$label: ")
                                    }
                                    withStyle(style = SpanStyle(fontSize = 16.sp)) {
                                        append(value)
                                    }
                                },
                                textAlign = TextAlign.Start,
                                lineHeight = 30.sp,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                }
            }
        }
    }
}
