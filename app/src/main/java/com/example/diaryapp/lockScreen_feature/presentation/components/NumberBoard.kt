package com.example.diaryapp.lockScreen_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



private val NumberButtonBackground = Color.Black.copy(alpha = 0.1F)
// method 2
@Composable
fun NumberBoard(
    onNumberClick: (num: String) -> Unit,
) {
    val buttons = (1..9).toList()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        buttons.chunked(3).forEach { buttonRow ->
            Row(
            ) {
                buttonRow.forEach { buttonNumber ->

                    NumberButton(
                        number = buttonNumber.toString(),
                        onClick = onNumberClick,
                        modifier = Modifier
                            .weight(1f)
                    )

                }
            }
        }
        NumberBoardRow(listOf(".", "0", "X"), onNumberClick = onNumberClick)
    }
}

//@Composable
//fun NumberBoardRow(num: List<String>, onNumberClick: (num: String) -> Unit) {
//    val list = (1..9).map { it.toString() }.toMutableList()
//    list.addAll(mutableListOf(".", "0", "X"))
//
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(3),
//        contentPadding = PaddingValues(
//            start = 12.dp,
//            top = 16.dp,
//            end = 12.dp,
//            bottom = 16.dp
//        ),
//        content = {
//            itemsIndexed(items = list) { index, item ->
//                NumberButton(
//                    modifier = Modifier,
//                    number = item,
//                    onClick = { onNumberClick(it) })
//
//            }
//        }
//    )
//}

//method 2 function
@Composable
fun NumberBoardRow(
    num: List<String>,
    onNumberClick: (num: String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in num) {
            NumberButton(
                modifier = Modifier.weight(1f),
                number = i,
                onClick = { onNumberClick(it) })
        }
    }
}