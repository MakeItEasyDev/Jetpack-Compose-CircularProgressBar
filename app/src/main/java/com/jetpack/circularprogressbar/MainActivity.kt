package com.jetpack.circularprogressbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetpack.circularprogressbar.component.DecreaseCircularProgressBar
import com.jetpack.circularprogressbar.ui.theme.CircularProgressBarTheme
import com.jetpack.circularprogressbar.ui.theme.Purple500
import com.jetpack.circularprogressbar.viewmodel.CircularProgressViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CircularProgressBarTheme {
                CircularProgressBar()
            }
        }
    }
}

@Composable
fun CircularProgressBar() {
    val scaffoldState = rememberScaffoldState()
    val viewModel: CircularProgressViewModel = viewModel()
    val decreaseSec = viewModel.decreaseSec.observeAsState()
    val increaseSec = viewModel.increaseSec.observeAsState()

    viewModel.decreaseCountDown(30000L)
    viewModel.increaseCountDown(30000L)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Box() {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Purple500)
                            .padding(15.dp)
                    ) {
                        Text(
                            text = "Circular ProgressBar",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Decrease CircularProgressBar",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black,
                        )

                        Spacer(modifier = Modifier.padding(10.dp))

                        DecreaseCircularProgressBar(percentage = decreaseSec.value!!.toFloat())

                        Spacer(modifier = Modifier.padding(30.dp))

                        Text(
                            text = "Increase CircularProgressBar",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black,
                        )

                        Spacer(modifier = Modifier.padding(10.dp))

                        DecreaseCircularProgressBar(percentage = increaseSec.value!!.toFloat())
                    }
                }
            }
        }
    }
}

























