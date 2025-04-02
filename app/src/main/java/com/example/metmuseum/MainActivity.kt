package com.example.metmuseum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.metmuseum.ui.search.SearchScreen
import com.example.metmuseum.ui.search.SearchRoute
import com.example.metmuseum.ui.theme.METmuseumTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            METmuseumTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "ElementsRoute",
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable("ElementsRoute") {
                        // Pass a simple lambda for onFavTextClick
                        SearchScreen(
                            onResultClick = { id ->
                                navController.navigate("SearchRoute/$id") // Navigate to a result detail screen (simplified for now)
                            },
                            onFavTextClick = {
                                // Handle the favorite click here, for now, we can just print the action
                                println("Favorite clicked!")
                            }
                        )
                    }

                    composable("SearchRoute/{id}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")
                        id?.let {
                            Text(text = "Clicked on item with ID: $id")  // Placeholder for now
                        }
                    }
                }
            }
        }
    }
}

