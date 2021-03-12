package com.example.myapplication.data.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.data.view.ui.theme.MyApplicationTheme

class LoginSignUpActivity : AppCompatActivity() {
    @ExperimentalFocus
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                LoginApplication()
            }
        }
    }


    @ExperimentalFocus
    @Composable
    fun LoginApplication() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "login_page", builder = {
            composable("login_page", content = { LoginPage(navController = navController) })
            composable("register_page", content = { RegisterPage(navController = navController) })
        })
    }


    @ExperimentalFocus
    @Composable
    fun LoginPage(navController: NavController) {

        val image = imageResource(id = R.drawable.login_image)

        val emailValue = remember { mutableStateOf("") }
        val passwordValue = remember { mutableStateOf("") }

        val passwordVisibility = remember { mutableStateOf(false) }
        val focusRequester = remember { FocusRequester() }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Box(
                    modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White), contentAlignment = Alignment.TopCenter
            ) {

                Image(image)
            }

            Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.60f)
                            .clip(RoundedCornerShape(topLeft = 30.dp, topRight = 30.dp))
                            .background(Color.White)
                            .padding(10.dp)
            ) {

                ScrollableColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                            text = "Sign In",
                            style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = TextUnit.Sp(2)
                            ),
                            fontSize = TextUnit.Sp(30)
                    )
                    Spacer(modifier = Modifier.padding(20.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OutlinedTextField(
                                value = emailValue.value,
                                onValueChange = { emailValue.value = it },
                                label = { Text(text = "Email Address") },
                                placeholder = { Text(text = "Email Address") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(0.8f),
                                onImeActionPerformed = { _, _ ->
                                    focusRequester.requestFocus()
                                }
                        )

                        OutlinedTextField(
                                value = passwordValue.value,
                                onValueChange = { passwordValue.value = it },
                                trailingIcon = {
                                    IconButton(onClick = {
                                        passwordVisibility.value = !passwordVisibility.value
                                    }) {
                                        Icon(
                                                imageVector = vectorResource(id = R.drawable.password_eye),
                                                tint = if (passwordVisibility.value) primaryColor else Color.Gray
                                        )
                                    }
                                },
                                label = { Text("Password") },
                                placeholder = { Text(text = "Password") },
                                singleLine = true,
                                visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                                else PasswordVisualTransformation(),
                                modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .focusRequester(focusRequester = focusRequester),
                                onImeActionPerformed = { _, controller ->
                                    controller?.hideSoftwareKeyboard()
                                }

                        )

                        Spacer(modifier = Modifier.padding(10.dp))
                        Button(
                                onClick = {},
                                modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .height(50.dp)
                        ) {
                            Text(text = "Sign In", fontSize = TextUnit.Sp(20))
                        }

                        Spacer(modifier = Modifier.padding(20.dp))
                        Text(
                                text = "Create An Account",
                                modifier = Modifier.clickable(onClick = {
                                    navController.navigate("register_page") {
                                        popUpTo = navController.graph.startDestination
                                        launchSingleTop = true
                                    }
                                })
                        )
                        Spacer(modifier = Modifier.padding(20.dp))
                        Text(
                                text = "Main Page",
                                modifier = Modifier.clickable(onClick = {
                                    startActivity(Intent(this@LoginSignUpActivity, MainActivity::class.java))
                                })
                        )
                    }
                }
            }

        }
    }
    private val primaryColor = Color(0xFF7048B6)
    @Composable
    fun RegisterPage(navController: NavController) {

        val image = imageResource(id = R.drawable.register_page)

        val nameValue = remember { mutableStateOf("") }
        val emailValue = remember { mutableStateOf("") }
        val phoneValue = remember { mutableStateOf("") }
        val passwordValue = remember { mutableStateOf("") }
        val confirmPasswordValue = remember { mutableStateOf("") }

        val passwordVisibility = remember { mutableStateOf(false) }
        val confirmPasswordVisibility = remember { mutableStateOf(false) }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Box(
                    modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                    contentAlignment = Alignment.TopCenter
            ) {
                Image(image)
            }


            Column(
                    modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.70f)
                            .clip(RoundedCornerShape(topLeft = 30.dp, topRight = 30.dp))
                            .background(Color.White)
                            .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
            ) {
                ScrollableColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                            text = "Sign Up", fontSize = TextUnit.Sp(30),
                            style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = TextUnit.Companion.Sp(2)
                            )
                    )
                    Spacer(modifier = Modifier.padding(20.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OutlinedTextField(
                                value = nameValue.value,
                                onValueChange = { nameValue.value = it },
                                label = { Text(text = "Name") },
                                placeholder = { Text(text = "Name") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(0.8f)
                        )

                        OutlinedTextField(
                                value = emailValue.value,
                                onValueChange = { emailValue.value = it },
                                label = { Text(text = "Email Address") },
                                placeholder = { Text(text = "Email Address") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(0.8f)
                        )

                        OutlinedTextField(
                                value = phoneValue.value,
                                onValueChange = { phoneValue.value = it },
                                label = { Text(text = "Phone Number") },
                                placeholder = { Text(text = "Phone Number") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(0.8f),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                        )

                        OutlinedTextField(
                                value = passwordValue.value,
                                onValueChange = { passwordValue.value = it },
                                label = { Text(text = "Password") },
                                placeholder = { Text(text = "Password") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(0.8f),
                                trailingIcon = {
                                    IconButton(onClick = {
                                        passwordVisibility.value = !passwordVisibility.value
                                    }) {
                                        Icon(
                                                imageVector = vectorResource(id = R.drawable.password_eye),
                                                tint = if (passwordVisibility.value) primaryColor else Color.Gray
                                        )
                                    }
                                },
                                visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                                else PasswordVisualTransformation()
                        )

                        OutlinedTextField(
                                value = confirmPasswordValue.value,
                                onValueChange = { confirmPasswordValue.value = it },
                                label = { Text(text = "Confirm Password") },
                                placeholder = { Text(text = "Confirm Password") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(0.8f),
                                trailingIcon = {
                                    IconButton(onClick = {
                                        confirmPasswordVisibility.value = !confirmPasswordVisibility.value
                                    }) {
                                        Icon(
                                                imageVector = vectorResource(id = R.drawable.password_eye),
                                                tint = if (confirmPasswordVisibility.value) primaryColor else Color.Gray
                                        )
                                    }
                                },
                                visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
                                else PasswordVisualTransformation()
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                        Button(onClick = { }, modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(50.dp)) {
                            Text(text = "Sign Up", fontSize = TextUnit.Companion.Sp(20))
                        }
                        Spacer(modifier = Modifier.padding(20.dp))
                        Text(
                                text = "Login Instead",
                                modifier = Modifier.clickable(onClick = {
                                    navController.navigate("login_page"){
                                        popUpTo = navController.graph.startDestination
                                        launchSingleTop = true
                                    }
                                })
                        )
                        Spacer(modifier = Modifier.padding(20.dp))

                    }

                }
            }
        }
    }

}

