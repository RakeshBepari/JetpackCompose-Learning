package com.example.jetpackcomposelearning.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposelearning.ui.theme.JetpackComposeLearningTheme

class LoginActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeLearningTheme {
                Surface(color = MaterialTheme.colors.background ){
                    LoginScreen()
                }
            }
            
        }
    }

    @Composable
    fun LoginScreen(){

        val userName = remember{ mutableStateOf("") }
        val password = remember{ mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Login Screen",
                color = Color.Blue,
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Enter Credentials to Login",
                color = Color.Blue,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace
            )
            OutlinedTextField(
                value = userName.value,
                onValueChange = {
                    userName.value = it
                },
                leadingIcon = {
                    Icon(Icons.Filled.Person,contentDescription = "Login Username")
                },
                label= {
                    Text(text = "UserName")
                },
                placeholder = {
                    Text(text = "Enter User Name")
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                leadingIcon = {
                    Icon(Icons.Filled.Info,contentDescription = "Password")
                },
                label= {
                    Text(text = "Password")
                },
                placeholder = {
                    Text(text = "Enter Password")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { Logged(userName.value,password.value) },
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            ) {
                Icon(Icons.Filled.AccountBox,contentDescription = "Submit Button")
                Text(text = "Submit")
            }


        }
    }
    private fun Logged(userName:String,password:String){
        if (userName == "123" && password== "123"){
            Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Not Logged In", Toast.LENGTH_SHORT).show()
        }
    }
}