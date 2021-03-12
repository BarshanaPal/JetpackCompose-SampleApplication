package com.example.myapplication.data.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.myapplication.data.model.Screen
import com.example.myapplication.data.viewmodel.DogListViewModel
import com.example.myapplication.ui.theme.AppTheme

import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: DogListViewModel

    // should be saved in data store
    private val isDark = mutableStateOf(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DogListViewModel::class.java)
        setContent {
            AppTheme(darkTheme = isDark.value) {
                MainScreen()
            }
        }
    }
    @Composable
    fun MainScreen(){
        val navController= rememberNavController()
        val title= remember { mutableStateOf("Home") }
        Scaffold(
                topBar = {
                    TopAppBar(title = { Text(text = title.value) },
                            actions = {
                                IconButton(onClick = {}) {
                                    Icon(Icons.Default.Share)
                                }
                            })
                         } ,
                bottomBar = {
                    val items = listOf(Screen.Home, Screen.Search, Screen.DateTime)
                    BottomNavigation {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

                        items.forEach {
                            BottomNavigationItem(
                                    icon = { Icon(it.icon) },
                                    selected = currentRoute == it.route,
                                    label = { Text(text = it.label) },
                                    onClick = {
                                        navController.popBackStack(
                                                navController.graph.startDestination, false)
                                        if (currentRoute != it.route) {
                                            navController.navigate(it.route)
                                        }
                                    })
                        }
                    }
                }
        ) {
            ScreenController(navController, title)
        }
    }

    @Composable
    fun ScreenController(navController: NavHostController, title: MutableState<String>) {
        NavHost(navController = navController, startDestination = "Home") {
            composable("Home") {
                HomeScreen()
                title.value = "Home"
            }

            composable("Search") {
                SearchScreen(onToggleTheme = {
                    toggleLightTheme()
                })
                title.value = "Search"
            }

            composable("DateTime") {
                DateTimeScreen()
                title.value = "Date Time"
            }

        }
    }

    @Composable
    fun DateTimeScreen() {
        Box(
                modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray),
                contentAlignment = Alignment.Center) {
            Column {
                val date by remember { mutableStateOf(date()) }
                Text(text=date,style= TextStyle(fontSize = 30.sp,fontFamily = FontFamily.Cursive))

                val time by remember { mutableStateOf(time()) }
                Text(text=time,style= TextStyle(fontSize = 30.sp,fontFamily = FontFamily.Cursive))
            }

        }
    }

    @Composable
    fun SearchScreen(onToggleTheme:()->Unit) {
        val query by remember { mutableStateOf("") }
        Column {
            Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    elevation = 8.dp)
            {
                Row(modifier = Modifier.fillMaxWidth()) {
                    TextField(
                            modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .padding(8.dp),
                            value = query,
                            onValueChange = {},
                            label = { Text(text = "Search") },
                            keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Search),
                            leadingIcon = { Icon(Icons.Filled.Search) },
                            onImeActionPerformed = { action, softKeyBoardController ->
                                if (action == ImeAction.Search) {
                                    softKeyBoardController?.hideSoftwareKeyboard()
                                }
                            },
                            textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                            backgroundColor = MaterialTheme.colors.surface
                    )
                    ConstraintLayout(
                            modifier = Modifier
                                    .align(Alignment.CenterVertically)
                    ) {
                        val menu = createRef()
                        IconButton(
                                onClick = onToggleTheme,
                                modifier = Modifier.constrainAs(menu) {
                                    end.linkTo(parent.end)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                }
                        ) {
                            Icon(Icons.Filled.MoreVert)
                        }
                    }
                }
            }
            viewModel.Dogs()
        }
    }

    @Composable
    fun HomeScreen() {
        viewModel.Dogs()

    }

    private fun date(): String {
        val currentDate= Calendar.getInstance().time
        return DateFormat.getDateInstance(DateFormat.FULL).format(currentDate)
    }

    private fun time(): String {
        val currentTime= Calendar.getInstance().time
        return DateFormat.getTimeInstance(DateFormat.MEDIUM).format(currentTime)
    }
    private fun toggleLightTheme() {
        isDark.value = !isDark.value
    }
}

