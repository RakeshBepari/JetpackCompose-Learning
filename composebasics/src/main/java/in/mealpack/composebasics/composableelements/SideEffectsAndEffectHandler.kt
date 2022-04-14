package `in`.mealpack.composebasics.composableelements

import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import kotlinx.coroutines.delay

@Composable
fun SideEffectsAndEffectHandler() {
    ProduceStateUsage()
}

@Composable
fun ProduceStateUsage(){
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState) {
        var counter = produceState(initialValue = 0){
            delay(5000)
            value = 5
        }
        if (counter.value % 5 == 0 && counter.value > 0) {
            LaunchedEffect(key1 = scaffoldState.snackbarHostState)  {
                scaffoldState.snackbarHostState.showSnackbar("Hello ${counter.value}")
            }
        }
        Button(onClick = { }) {
            Text(text = "Click me ${counter.value}")

        }
    }
}

@Composable
fun LaunchEffectUsage(){
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState) {
        var counter by remember {
            mutableStateOf(0)
        }
        if (counter % 5 == 0 && counter > 0) {
            LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
                scaffoldState.snackbarHostState.showSnackbar("Hello $counter")
            }
        }
        Button(onClick = { counter++ }) {
            Text(text = "Click me $counter")

        }
    }
}