package com.marcosmontiel.lifecycle

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.marcosmontiel.lifecycle.ui.theme.LifecycleTheme

class MainActivity : ComponentActivity() {

    private var _position: Int = 0
    private lateinit var _mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            LifecycleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DialogCard()
                    Log.i("Lifecycle", "onCreate")
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        _mediaPlayer = MediaPlayer.create(this, R.raw.soundtrack)
        Log.i("Lifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        _mediaPlayer.seekTo(_position)
        _mediaPlayer.start()
        Log.i("Lifecycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        _mediaPlayer.pause()
        _position = _mediaPlayer.currentPosition
        Log.i("Lifecycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        _mediaPlayer.stop()
        _mediaPlayer.release()
        Log.i("Lifecycle", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Lifecycle", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Lifecycle", "onDestroy")
    }

}

@Composable
fun DialogCard() {

    var showDialog by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                content = {

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(text = "Lifecycle")

                        Button(onClick = { showDialog = true }) {
                            Text(text = "Show details")
                        }

                        if (showDialog) {

                            Dialog(onDismissRequest = { showDialog = false }) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.White)
                                ) {
                                    Text(text = "Hello")
                                }
                            }

                        }

                    }

                }
            )

        }
    )
}