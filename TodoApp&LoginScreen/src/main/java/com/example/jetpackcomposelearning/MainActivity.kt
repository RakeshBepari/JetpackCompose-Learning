package com.example.jetpackcomposelearning

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposelearning.ui.theme.JetpackComposeLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeLearningTheme {
                // A surface container using the 'background' color from the theme
                Scaffold (
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Rohan")
                            },
                            navigationIcon = {
                                IconButton(onClick = {}) {
                                    Icon(Icons.Filled.Menu,contentDescription = "menu")
                                }
                            },
                            actions = {
                                IconButton(onClick = { }) {
                                    Icon(Icons.Filled.ShoppingCart,contentDescription = "cart")
                                }
                                IconButton(onClick = {}) {
                                    Icon(Icons.Filled.Notifications,contentDescription = "notification")
                                }
                            }
                        )
                    },
                    drawerContent = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Filled.Person,contentDescription = "Contact us")
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Filled.ExitToApp,contentDescription = "Sign out")
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { /*TODO*/ }) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Filled.Add,contentDescription = "Add")
                            }
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center
                        ){
                            showSwitch()
                        }
            }
        }
    }
}

@Composable
fun showSwitch(){
    val isChecked = remember{
        mutableStateOf(true)
    }
    val context = LocalContext.current

    Text(
        text = "$isChecked!",
        fontSize = 32.sp,
        color = colorResource(id = R.color.purple_700),
        fontFamily = FontFamily.Cursive,
        modifier = Modifier.clickable {
            Toast.makeText(context , "hello", Toast.LENGTH_SHORT).show()
        }
    )

    Switch(
        checked =isChecked.value,
        onCheckedChange = {
            isChecked.value = it
        },
        modifier = Modifier.apply {
            size(20.dp)
            padding(100.dp)
        }
    )
}

@Composable
fun Greeting(name: String) {
    val context = LocalContext.current
    Text(
        text = "Hello $name!",
        fontSize = 32.sp,
        color = colorResource(id = R.color.purple_700),
        fontFamily = FontFamily.Cursive,
        modifier = Modifier.clickable {
            Toast.makeText(context , "hello", Toast.LENGTH_SHORT).show()
        }
    )
}

@Preview(showBackground = true,name = "light mode")
@Preview(showBackground = true,name = "dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    JetpackComposeLearningTheme{
        Surface(Modifier.fillMaxSize()) {
            Greeting("kaju")
        }
    }
}