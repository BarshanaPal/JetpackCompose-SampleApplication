package com.example.myapplication.data.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.data.model.DogBreedItem
import com.example.myapplication.data.util.DEFAULT_DOG_IMAGE
import com.example.myapplication.data.util.LoadPicture

@Composable
fun DogCard(
        dogBreedItem: DogBreedItem,
        onClick:()->Unit
){
    Card(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .padding(
                    bottom = 6.dp,
                    top = 6.dp
                )
                .fillMaxWidth()
                .clickable(onClick = onClick),
            elevation = 8.dp)
    {
        Column{
            dogBreedItem.url?.let {url->
                val image= LoadPicture(url = url, default_image = DEFAULT_DOG_IMAGE).value
                image?.let {img->
                    Image(bitmap = img.asImageBitmap(),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

            }
            dogBreedItem.name?.let { name->
                Row(
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
                ) {
                    Text(
                            text=name,
                            modifier = Modifier
                                    .fillMaxWidth(0.65f)
                                    .wrapContentWidth(Alignment.Start),
                            style=MaterialTheme.typography.h6
                    )
                    Text(
                            text = dogBreedItem.life_span.toString(),
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.End)
                                    .align(Alignment.CenterVertically),
                            style=MaterialTheme.typography.h6
                    )
                }

            }
        }
    }
}