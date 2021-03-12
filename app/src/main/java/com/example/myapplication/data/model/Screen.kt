package com.example.myapplication.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(val route:String,val label:String,val icon:ImageVector){
    object Home: Screen("Home","Home", Icons.Default.Home)
    object Search: Screen("Search","Search",Icons.Default.Search)
    object DateTime: Screen("DateTime","DateTime",Icons.Default.DateRange)
}
