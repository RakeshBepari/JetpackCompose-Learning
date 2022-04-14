package com.example.jetpackcomposelearning.ui

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.jetpackcomposelearning.data.Todo
import com.example.jetpackcomposelearning.ui.theme.JetpackComposeLearningTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoActivity:ComponentActivity() {

    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeLearningTheme {
                Surface (color = MaterialTheme.colors.background) {
                    AddToolBar()
                }
            }
        }
    }

    @Composable
    fun AddToolBar(){
        Scaffold(
            topBar ={
                TopAppBar(
                    title = {
                        Text(text = "TodoApp")
                    }
                )
            },
            floatingActionButton = {
                val openDialog = remember {
                    mutableStateOf(false)
                }
                FloatingActionButton(onClick = {openDialog.value = true}) {
                    AddDialogBox(openDialog = openDialog)
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        ) {
            AllNotes(todoViewModel = todoViewModel)
        }
    }

    @Composable
    fun AddDialogBox(openDialog:MutableState<Boolean>){

        val title = remember { mutableStateOf("")}
        val description = remember { mutableStateOf("")}

        if (openDialog.value){
            AlertDialog(
                onDismissRequest = {
                    openDialog.value =false
                },
                title = {
                    Text(text = "ToDo")
                },
                text = {
                    LazyColumn (
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),)
                    {
                        items(listOf("")){
                        OutlinedTextField(
                            value = title.value,
                            onValueChange = {
                                title.value = it },
                            placeholder = {
                                Text(text = "Enter Title")
                            },
                            label = {
                                Text(text = "Enter Title")
                            },
                            modifier = Modifier.padding(10.dp)
                        )
                        OutlinedTextField(
                            value = description.value,
                            onValueChange = {
                                description.value = it },
                            placeholder = {
                                Text(text = "Enter Description")
                            },
                            label = {
                                Text(text = "Enter Description")
                            },
                            modifier = Modifier.padding(10.dp)
                        )
                    }}
                },
                confirmButton = {
                    OutlinedButton(
                        onClick = {
                            insert(title,description)
                            openDialog.value = false
                            title.value = ""
                            description.value= ""
                        }
                    ) {
                        Text(text = "Save")
                    }
                }
            )
        }
    }

    private fun insert(title:MutableState<String>, description:MutableState<String>){
        lifecycleScope.launchWhenStarted {
            if (!TextUtils.isEmpty(title.value) && !TextUtils.isEmpty(description.value))    {
                todoViewModel.insertTodo(Todo(title.value,description.value))
                Toast.makeText(this@TodoActivity, "Empty...", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this@TodoActivity, "Empty...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Composable
    fun Note(todo: Todo){
        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            shape = RoundedCornerShape(5.dp)
        ) {
                Row {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(text = todo.title, fontWeight = FontWeight.ExtraBold)
                        Text(text = todo.description, fontStyle = FontStyle.Italic)
                    }

                    Spacer(modifier = Modifier.width(200.dp))

                    IconButton(onClick = { deleteTodo(todo) }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete Todo")
                    }
                }

        }
    }

    private fun deleteTodo(todo: Todo) {
        todoViewModel.deleteTodo(todo)
    }

    @Composable
    fun AllNotes(todoViewModel: TodoViewModel){
        LazyColumn{
            items(todoViewModel.response.value){todo->
                Note(todo = todo)
            }
        }

    }

    @Preview
    @Composable
    fun ShowPreview(){
        JetpackComposeLearningTheme {
            AddToolBar()
        }
    }
}