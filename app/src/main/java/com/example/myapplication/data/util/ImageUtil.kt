package com.example.myapplication.data.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.AmbientContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.myapplication.R

const val DEFAULT_DOG_IMAGE= R.drawable.dog_icon
@Composable
fun LoadPicture(
    url:String,
    @DrawableRes default_image:Int
):MutableState<Bitmap?>{
    val bitMapState:MutableState<Bitmap?> = mutableStateOf(null)
    Glide.with(AmbientContext.current)
        .asBitmap()
        .load(default_image)
        .into(object :CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitMapState.value=resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
    Glide.with(AmbientContext.current)
        .asBitmap()
        .load(url)
        .into(object :CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitMapState.value=resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
    return bitMapState
}