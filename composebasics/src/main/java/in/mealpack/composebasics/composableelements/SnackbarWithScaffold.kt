package `in`.mealpack.composebasics.composableelements

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun SnackbarWithScaffold() {

    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()

    var textField by remember {
        mutableStateOf("")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {
            TextField(
                value = textField,
                onValueChange = {
                    textField = it
                },
                label = {
                    Text(text = "Enter your name")
                },
                singleLine = true,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                // WARNING : NEVER LAUNCH A COROUTINE DIRECTLY IN COMPOSABLE,
//                           IT'S ONLY OKAY IN CALLBACKS SUCH AS AN onClickListener
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Hello $textField")
                }
            }) {
                Text(text = "Greet Me")
            }
        }
    }
}