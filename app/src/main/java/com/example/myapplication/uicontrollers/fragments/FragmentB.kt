package com.example.myapplication.uicontrollers.fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodels.CounterViewModel

class FragmentB : Fragment() {

    private lateinit var viewModel: CounterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val activityContext = requireActivity()
        viewModel = ViewModelProvider(activityContext)[CounterViewModel::class.java]
        return ComposeView(requireContext()).apply {
            setContent {
                ResultComposeView()
            }
        }
    }


    @Composable
    fun ResultComposeView() {
        MyApplicationTheme {
            Surface(
                modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(viewModel.getCounterList()) { doubleItem ->
                        Text(text = doubleItem.toString())
                    }
                }
            }
        }
    }
}