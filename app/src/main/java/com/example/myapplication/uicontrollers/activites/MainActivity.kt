package com.example.myapplication.uicontrollers.activites

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.viewmodels.CounterViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.commit

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: CounterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(CounterViewModel::class.java)

        setContent {
            MainContent()
        }
    }

    @Composable
    fun MainContent(){
    }

    @Composable
    fun InputComposeView() {
        MyApplicationTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    val textAmount: MutableState<Int> = remember { mutableIntStateOf(0) }
                    val textTime: MutableState<Long> = remember { mutableLongStateOf(0) }
                    Text(
                        text =
                        String.format("Amount :%,d", textAmount.value),
                        modifier = Modifier.padding(top = 50.dp),
                        style = TextStyle(
                            color = Color.Blue, fontSize = 16.sp, fontWeight = FontWeight.Bold
                        )
                    )
                    TextField(value = textAmount.value.toString(),
                        modifier = Modifier.padding(top = 50.dp),
                        onValueChange = { newText ->
                            val newValue = newText.toIntOrNull() ?: 0
                            textAmount.value = newValue
                        })
                    Text(
                        text =
                        formatSeconds(textTime.value),
                        modifier = Modifier.padding(top = 50.dp),
                        style = TextStyle(
                            color = Color.Blue, fontSize = 16.sp, fontWeight = FontWeight.Bold
                        )
                    )
                    TextField(value = textTime.value.toString(),
                        modifier = Modifier.padding(top = 50.dp),
                        onValueChange = { newText ->
                            val newValue = newText.toLongOrNull() ?: 0
                            textTime.value = newValue
                        })
                    Button(
                        onClick = {
                            viewModel.addNewCounter((textAmount.value * textTime.value).toDouble())
                            viewModel.getCounterList()
                            //TODO 跳转
                        }) {
                        Text(text = "Submit")
                    }
                }
            }
        }
    }

    @Composable
    fun ResultComposeView(){
        MyApplicationTheme {
            Surface(
                modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
            ){
                LazyColumn (
                    modifier = Modifier.fillMaxSize()
                ){
                    items(viewModel.getCounterList()) { doubleItem  ->
                        Text(text  = doubleItem.toString())
                    }
                }
            }
        }
    }

    @Composable
    fun FragmentA() {
        InputComposeView()
    }

    @Composable
    fun FragmentB() {
        ResultComposeView()
    }

    private fun formatSeconds(seconds: Long): String {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val remainingSeconds = seconds % 60
        return String.format("%2dh%2dm%2ds", hours, minutes, remainingSeconds)
    }
}