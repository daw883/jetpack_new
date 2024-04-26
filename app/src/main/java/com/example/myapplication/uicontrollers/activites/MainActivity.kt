package com.example.myapplication.uicontrollers.activites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.viewmodels.CounterViewModel


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
}