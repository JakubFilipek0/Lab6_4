package edu.pbs.lab6_4.screens.detals

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import edu.pbs.lab6_4.model.Movie
import edu.pbs.lab6_4.model.getMovies
import edu.pbs.lab6_4.widgets.MovieRow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navController: NavController, movieId: Int?) {
    val newmovieList = getMovies().filter { movie -> movie.id == movieId }
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.LightGray, elevation = 5.dp) {
                Row(horizontalArrangement = Arrangement.Start) {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow back",
                        Modifier.clickable {
                            navController.popBackStack()
                        })
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(text = "Movies")
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                MovieRow(movie = newmovieList.first())
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(text = "Movie images")
                HorizontalScrollableImageView(newmovieList)
            }

        }
    }
}

@Composable
private fun HorizontalScrollableImageView(newmovieList: List<Movie>) {
    LazyRow {
        items(newmovieList[0].images) { image ->
            Card(modifier = Modifier
                .padding(12.dp)
                .size(240.dp), elevation = 5.dp) {
                val painter = rememberAsyncImagePainter(image)
                val state = painter.state
                if (state is AsyncImagePainter.State.Success) {

                }
                Image(
                    painter = painter,
                    contentDescription = "Movie poster"
                )
            }
        }
    }
}