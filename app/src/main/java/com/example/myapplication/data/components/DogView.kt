package com.example.myapplication.data.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.model.DogBreedItem
import com.example.myapplication.data.util.DEFAULT_DOG_IMAGE
import com.example.myapplication.data.util.LoadPicture

@Composable
fun DogView(
        dogBreedItem: DogBreedItem
){
    ScrollableColumn(modifier=Modifier.fillMaxWidth()) {
        dogBreedItem.url?.let { url->
            val image= LoadPicture(url = url, default_image = DEFAULT_DOG_IMAGE).value
            image?.let {img->
                Image(bitmap = img.asImageBitmap(),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                )
            }
        }
        Column(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
            dogBreedItem.name?.let {title->
                Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                    Text(text=title,
                         modifier = Modifier
                                 .fillMaxWidth(0.65f)
                                 .wrapContentWidth(Alignment.Start),
                         style=MaterialTheme.typography.h4
                    )
                    val lifeSpan=dogBreedItem.life_span.toString()
                    Text(text=lifeSpan,
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.End)
                                    .align(Alignment.CenterVertically),
                            style=MaterialTheme.typography.h6
                    )
                }
                dogBreedItem.bred_for?.let { bred_for->
                    Text(text = bred_for,
                         modifier = Modifier
                                 .fillMaxWidth()
                                 .padding(bottom = 8.dp),
                            style = MaterialTheme.typography.h5
                    )
                }
                dogBreedItem.breed_group?.let { breed_group->
                    Text(text = breed_group,
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                            style = MaterialTheme.typography.h5
                    )
                }
                dogBreedItem.temperament?.let { temperament->
                    Text(text=temperament,
                         modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                         style = MaterialTheme.typography.h5
                    )
                }
                dogBreedItem.origin?.let { origin->
                    Text(text=origin,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                            style = MaterialTheme.typography.h5
                    )
                }
                dogBreedItem.description?.let { description->
                    Text(text=description,
                         modifier = Modifier
                                 .fillMaxWidth()
                                 .padding(bottom = 8.dp),
                         style = MaterialTheme.typography.h5
                    )
                }

            }
        }
    }
}