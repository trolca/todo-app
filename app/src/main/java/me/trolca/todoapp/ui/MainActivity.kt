package me.trolca.todoapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import me.trolca.todoapp.TodoApplication
import me.trolca.todoapp.data.TodoListView
import me.trolca.todoapp.data.TodoListViewFactory
import me.trolca.todoapp.data.database.TodoTask
import me.trolca.todoapp.ui.components.TodoBox
import me.trolca.todoapp.ui.theme.KotlinAppTheme
import androidx.compose.foundation.lazy.items

class MainActivity() : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val todoApplication = TodoApplication(applicationContext);

        enableEdgeToEdge()
        setContent {
            KotlinAppTheme {
                val todoVM: TodoListView = viewModel(TodoListView::class ,factory = TodoListViewFactory(todoApplication.todoRepo))
                val tasks by todoVM.uiState.collectAsStateWithLifecycle()
                val alphaAnimation by animateFloatAsState( if(tasks.isEmpty()) 0.0f else 1.0f, label = "alphaButtonAppear" )

                Scaffold {

                    LazyColumn(userScrollEnabled = tasks.isNotEmpty(), modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                         items(tasks){ task ->
                            TodoBox(modifier = Modifier.alpha(alphaAnimation) ,task.taskDesc, task.finished){
                                todoVM.updateTask(todoTask = task, finished = it)
                            }
                        }
                    }

                    FloatingActionButton(
                        onClick = { todoVM.addTask(
                            TodoTask(
                                null,
                                "Fish this app please",
                                finished = false
                            )
                        )},
                        modifier = Modifier.size(80.dp).offset(
                            x = (LocalConfiguration.current.screenWidthDp - 100).dp,
                            y = (LocalConfiguration.current.screenHeightDp - 85).dp
                        )
                    ){
                        Icon(Icons.Filled.Add, "Add a task", Modifier.fillMaxSize(0.5f))
                    }

                }
            }
        }


    }
}