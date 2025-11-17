package com.example.hellocompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellocompose.ui.theme.HelloComposeTheme
import com.example.hellocompose.ui.theme.Pink40
import  androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloComposeTheme {
                Scaffold (
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
//                    HelloWorld()
                    Column (modifier = Modifier.fillMaxSize()){
                        CountView(
                            modifier = Modifier
                                .weight(1f)
                                .padding(16.dp)
                        )

                        CountView(
                            modifier = Modifier
                                .weight(1f)
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CountView(
    modifier: Modifier = Modifier
) {

    val ctx = LocalContext.current
    var count by rememberSaveable { mutableStateOf(0) }

    var currentToast: Toast? = null

    Column (modifier = modifier)
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = Color.Magenta),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Count: ${count}",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            Button(
                onClick = {
                    currentToast?.cancel()

                    currentToast = Toast.makeText(
                        ctx,
                        "$count Toasted",
                        Toast.LENGTH_SHORT
                    )

                    currentToast?.show()
                    count = 0
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Toast",
                    fontSize = 16.sp
                )
            }

            Button(
                onClick = {count++},
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Count",
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun HelloWorld() {
    val number = 4
    if (number < 5) {
        Text(text = "Bad", fontSize = 24.sp)
    } else {
        Text(text = "Nice", fontSize = 24.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun HelloWorldPreview() {
    Column (modifier = Modifier.fillMaxSize()){
        CountView(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        )

        CountView(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        )
    }
}