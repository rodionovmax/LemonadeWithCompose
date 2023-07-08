package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeLifecycle() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(1) }

    when (currentStep) {
        1 -> {
            LemonadeTextAndImage(
                drawableResourceId = R.drawable.lemon_tree,
                contentDescriptionResourceId = R.string.cd_lemon_tree,
                textLabelResourceId = R.string.lemon_tree_text,
                onImageClick = {
                    currentStep = 2
                    squeezeCount = (2..4).random()
                }
            )
        }
        2 -> {
            LemonadeTextAndImage(
                drawableResourceId = R.drawable.lemon_squeeze,
                contentDescriptionResourceId = R.string.cd_lemon,
                textLabelResourceId = R.string.lemon_text,
                onImageClick = {
                    squeezeCount--
                    if (squeezeCount == 0) {
                        currentStep = 3
                    }
                }
            )
        }
        3 -> {
            LemonadeTextAndImage(
                drawableResourceId = R.drawable.lemon_drink,
                contentDescriptionResourceId = R.string.cd_glass_of_lemonade,
                textLabelResourceId = R.string.glass_of_lemonade_text,
                onImageClick = {
                    currentStep = 4
                }
            )
        }
        4 -> {
            LemonadeTextAndImage(
                drawableResourceId = R.drawable.lemon_restart,
                contentDescriptionResourceId = R.string.cd_empty_glass,
                textLabelResourceId = R.string.empty_glass_text,
                onImageClick = {
                    currentStep = 1
                }
            )
        }
    }
}

@Composable
fun LemonadeTextAndImage(
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    textLabelResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier
                .width(260.dp)
                .aspectRatio(1f)
                .background(color = Color(0xFFC3ECD2), shape = RoundedCornerShape(30.dp))
                .clickable(onClick = onImageClick)
        ) {
            Image(
                painter = painterResource(id = drawableResourceId),
                contentDescription = stringResource(contentDescriptionResourceId),
                modifier = modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = stringResource(textLabelResourceId), textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        LemonadeLifecycle()
    }
}