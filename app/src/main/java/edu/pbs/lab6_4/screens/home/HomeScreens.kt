package edu.pbs.lab6_4.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.pbs.lab6_4.model.Movie
import edu.pbs.lab6_4.model.getMovies
import edu.pbs.lab6_4.navigation.MovieScreens
import edu.pbs.lab6_4.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Magenta, elevation = 5.dp) {
                Text(text = "Movies")
            }
        }
    ) { it ->
        Column(
            modifier = Modifier
                .padding(it)) {
            MainContent(navController = navController)
        }
    }
}

@Composable
fun MainContent(navController: NavController, moviesList: List<Movie> = getMovies()) {
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        LazyColumn {
            items(items = moviesList) {
                MovieRow(movie = it) { movie -> navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movie") }
            }
        }
    }
}