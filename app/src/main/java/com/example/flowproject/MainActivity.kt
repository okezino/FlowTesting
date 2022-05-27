package com.example.flowproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flowproject.ui.theme.FlowProjectTheme
import kotlinx.coroutines.flow.collect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowProjectTheme {
                val viewModel = viewModel<MainViewModel>()
                var value = 0

                //how to collect shared flow in compose
                LaunchedEffect(key1 = true){
               viewModel.sharedFlow.collect {
                   value = it
               }
                }


                //how to collect state flow  in compose
                val count = viewModel.stateValue.collectAsState(initial = 0)
                // A surface container using the 'background' color from the theme
                Box(modifier = Modifier.fillMaxSize()){
                    Button(onClick = { viewModel.incrementStateValue() }) {
                        Text(text = "Counter ${count.value}")
                    }
                }

                }
            }
        }
    }


