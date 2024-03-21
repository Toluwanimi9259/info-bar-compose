package com.techafresh.infobarcompose

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radusalagean.infobarcompose.InfoBar
import com.radusalagean.infobarcompose.InfoBarMessage
import com.techafresh.infobarcompose.ui.theme.InfoBarComposeTheme
import kotlin.math.log

class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InfoBarComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var message: InfoBarMessage? by remember { mutableStateOf(null) }
                    var customMessage : CustomMessage? by remember { mutableStateOf(null) }
                    var customInfoBarMessageWithActionButton : CustomInfoBarMessageWithActionButton? by remember { mutableStateOf(null) }
                    val ctx = LocalContext.current
                    
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = {
                            message = InfoBarMessage(
                                text = "Hello, This is a message",
                                backgroundColor = Color.Magenta,
                                displayTimeSeconds = 5
                            )
                        }) {
                            Text(text = "Show Normal InfoBar")
                        }
                        
                        Button(onClick = {
                            customMessage = CustomMessage(
                                textString = "This is a custom message",
                                textColor = Color(0xFF414141),
                                icon = Icons.Rounded.Info,
                                iconColor = Color(0xFF27C54D),
                                backgroundColor = Color(0xFFE3F1E6)
                            )
                        }) {
                            Text(text = "Show Custom Message 1")
                        }
                        
                        Button(onClick = {
                            customInfoBarMessageWithActionButton = CustomInfoBarMessageWithActionButton(
                                text = "Do you want to continue",
                                actionButton1Text = "Yes",
                                actionButton2Text = "No",
                                onActionButton1Clicked = {
                                    showToast(ctx,"Yes Button Clicked!")
                                },
                                onActionButton2Clicked = {
                                    showToast(ctx,"No Button Clicked!")
                                },
                                displayTimeSeconds = 5
                            )
                        }) {
                            Text(text = "Show Custom message 2")
                        }
                    }


                    // Normal InfoBar
                    InfoBar(
                        modifier = Modifier.padding(16.dp),
                        offeredMessage = message,
                        shape = RoundedCornerShape(5.dp),
                        elevation = 20.dp,
                        actionColor = Color.Magenta,
                    ) {
                         message = null
                    }



                    // Custom InfoBar 1
                    InfoBar(
                        modifier = Modifier.padding(16.dp),
                        shape = RoundedCornerShape(5.dp),
                        elevation = 10.dp,
                        offeredMessage = customInfoBarMessageWithActionButton,
                        content = customInfoBarContentWithActionButton
                    ) {
                        customInfoBarMessageWithActionButton = null
                    }



                    // Custom InfoBar 2
                    InfoBar(
                        modifier = Modifier.padding(16.dp),
                        shape = RoundedCornerShape(5.dp),
                        elevation = 10.dp,
                        offeredMessage = customMessage,
                        content = content
                    ) {
                        customMessage = null
                    }


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InfoBarComposeTheme {
        Greeting("Android")
    }
}

fun showToast(ctx : Context , message : String){
    Log.d("TAG", "showToast: $message")
    Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show()
}