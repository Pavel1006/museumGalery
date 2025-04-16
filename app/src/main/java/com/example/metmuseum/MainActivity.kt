package com.example.metmuseum

import DetailsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.metmuseum.ui.details.DetailsRoute
import com.example.metmuseum.ui.search.SearchScreen
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
                    startDestination = "SearchRoute",
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    modifier = Modifier,
                ) {
                    composable("SearchRoute") {
                        SearchScreen(
                            onResultClick = { id ->
                                navController.navigate(DetailsRoute(id))
                            }
                        )
                    }
                    composable<DetailsRoute> {
                        DetailsScreen(onUpClick = { navController.navigateUp() })
                    }
                }
            }
        }
    }
}





