package edmt.dev.kotlincarouselslider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import edmt.dev.kotlincarouselslider.ui.theme.KotlinCarouselSliderTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val images = listOf(
            "https://w0.peakpx.com/wallpaper/324/121/HD-wallpaper-new-2023-movie-john-wick-4-poster-movie.jpg",
            "https://w0.peakpx.com/wallpaper/694/975/HD-wallpaper-oppenheimer-movie-2023.jpg",
            "https://w0.peakpx.com/wallpaper/640/109/HD-wallpaper-transformers-rise-of-the-beasts-movie-poster-2023.jpg",
            "https://w0.peakpx.com/wallpaper/542/826/HD-wallpaper-optimus-prime-dragon-movie-poster-transformers-transformers-5-transformers-last-knight.jpg"
        )
        setContent {
            KotlinCarouselSliderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    CarouselSlider(images)
                }
            }
        }
    }

    @Composable
    private fun CarouselSlider(images: List<String>) {
        var index by remember { mutableStateOf(0) }
        val scrollState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(key1 = true, block = {
            coroutineScope.launch {
               while (true) {
                   delay(1000)
                   if (index == images.size - 1) index = 0
                   else index++
                   scrollState.animateScrollToItem(index)
               }
            }
        })

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                LazyRow(
                    state = scrollState,
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    itemsIndexed(images) { index, image ->
                        Card(
                            modifier = Modifier.height(500.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 8.dp
                            )
                        ) {
                            AsyncImage(
                                model = image,
                                contentDescription = "Image",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.width(300.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
