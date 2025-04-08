package com.example.catapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.catapp.R
import com.example.catapp.ui.theme.CatAppTheme
import com.example.catapp.ui.theme.DarkViolet
import com.example.catapp.ui.theme.LightViolet
import com.example.catapp.ui.theme.PurpleGrey40
import com.example.catapp.viewmodel.CatViewModel
import com.example.catapp.viewmodel.CatViewModel.CatState.Error
import com.example.catapp.viewmodel.CatViewModel.CatState.Idle
import com.example.catapp.viewmodel.CatViewModel.CatState.Success

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    ListCatScreen(Success(listOf()))
}

@Composable
fun ListCatScreen(state: CatViewModel.CatState) {
    CatAppTheme {
        Surface(
            color = LightViolet,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column {
                TitleText(
                    title = stringResource(R.string.app_name),
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)

                )
                when (state) {
                    is Success -> LazyColumn(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(state.catList) { cat ->
                            CardCat(urlId = cat.urlId)
                        }
                    }

                    is Idle -> {
                        Text(text = stringResource(R.string.cat_loading_text))
                    }

                    is Error -> {
                        Text(text = stringResource(R.string.cat_error_text))
                    }
                }

            }
        }
    }
}

@Composable
fun TitleText(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontFamily = FontFamily.Serif,
        color = DarkViolet,
        fontWeight = FontWeight.ExtraBold
    )
}

@Composable
fun CardCat(urlId: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(PurpleGrey40)
        ) {
            SubcomposeAsyncImage(
                model = urlId,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(painter, contentDescription)
            }
        }
    }
}