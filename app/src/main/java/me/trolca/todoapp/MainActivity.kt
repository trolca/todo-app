package me.trolca.todoapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import me.trolca.todoapp.mainui.components.TodoBox
import me.trolca.todoapp.ui.theme.KotlinAppTheme


class MainActivity : ComponentActivity() {

    private var hasClicked: Boolean = false
    private var text = mutableStateOf("")
    private var clicked: Int = 1

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {

            KotlinAppTheme {
                val config = LocalConfiguration.current;
                Scaffold {
                    Column {
                        Spacer(Modifier.size(1.dp, height = 30.dp))
                        Column(Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(10.dp)) {

                            TodoBox("Do smth")
                            TodoBox("Finish this app")
                            TodoBox("Make a todo app in android")
                            TodoBox("Create a very advanced app which stores your tasks in one place (a todo app)")
                            TodoBox("Finish this app")
                            TodoBox("Finish this app")
                            TodoBox("Finish this app")
                            TodoBox("Finish this app")

                        }
                    }
                }
            }
        }


    }
}