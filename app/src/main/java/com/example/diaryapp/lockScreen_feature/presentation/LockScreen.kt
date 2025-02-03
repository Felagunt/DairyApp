package com.example.diaryapp.lockScreen_feature.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.diaryapp.R
import com.example.diaryapp.lockScreen_feature.presentation.components.InputDots
import com.example.diaryapp.lockScreen_feature.presentation.components.NumberBoard
import com.example.diaryapp.lockScreen_feature.presentation.components.checkUserAuth

@Composable
fun LockScreen(
    toNavigate: () -> Unit
) {
    val pin = remember {
        mutableStateListOf<Int>(
        )
    }

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(10.dp))
//            Image(
//                painter = painterResource(id = R.drawable.png_transparent_diary_yellow_book),
//                contentDescription = "User",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(15.dp)
//                    .clip(CircleShape)
//            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Diary")

            Spacer(modifier = Modifier.weight(0.1f))
            Text(text = "Verify 4-digit security PIN")

            Spacer(modifier = Modifier.height(10.dp))


            InputDots(
                pin
            )

            Spacer(modifier = Modifier.weight(0.1f))

            NumberBoard(
                onNumberClick = { number ->
                    when(number) {
                        "." -> {}
                        "X" -> {
                            if(pin.isNotEmpty()) pin.removeLast()
                        }
                        else -> {
                            if(pin.size < 4) {
                                pin.add(number.toInt())
                            }
                        }
                    }
                }
            )




            Spacer(modifier = Modifier.height(10.dp))

            if (pin.size == 4) {
                checkUserAuth(
                    pin.toList(),
                    toNavigate
                )
            }
        }
    }
}